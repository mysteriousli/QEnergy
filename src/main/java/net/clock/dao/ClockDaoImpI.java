//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import main.java.net.clock.controller.Competer;
import main.java.net.clock.controller.ControllerTime;
import main.java.net.clock.entity.Analysis;
import main.java.net.clock.entity.Clock;
import main.java.net.clock.entity.Lable;
import main.java.tool.JdbcTool.DBBean;

public class ClockDaoImpI implements ClockDao {
    public ClockDaoImpI() {
    }
    //创建任务
    public int Create_Clock(Clock clock, int u_id) throws Exception {
        //创建数据库连接和命令对象
        Connection conn = DBBean.getConnection();
        int clockId = -1;
        String create_sql = "insert into tomato(c_start, c_duration, c_label, c_info, u_id) values(now(),?,?,?,?)";
        String select_sql = "select LAST_INSERT_ID(),now()";
        PreparedStatement create_stm = null;
        PreparedStatement select_stm = null;
        ResultSet rs = null;

        try {
            //插入语句
            create_stm = conn.prepareStatement(create_sql);
            //任务时长
            create_stm.setInt(1, Integer.parseInt(clock.getClockDuration()));
            //任务标签
            create_stm.setString(2, clock.getClockLabel());
            //任务备注
            create_stm.setString(3, clock.getClockInfo());
            //用户id
            create_stm.setInt(4, u_id);
            //执行命令
            create_stm.executeUpdate();
            select_stm = conn.prepareStatement(select_sql);
            rs = select_stm.executeQuery();
            rs.next();
            //获得任务id
            clockId = rs.getInt(1);
        } catch (Exception var11) {
            var11.printStackTrace();
        }
        //关闭数据库链接及查询语句
        rs.close();
        create_stm.close();
        select_stm.close();
        conn.close();
        return clockId;
    }
    //查询任务列表
    public List<Clock> Show_TodoList(int u_id) throws Exception {
        //创建数据库连接和命令对象
        Connection conn = DBBean.getConnection();
        String sql = "select * from tomato where u_id=? and c_full=1 order by c_start desc";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList clocks = new ArrayList();

        try {
            //查询语句
            stm = conn.prepareStatement(sql);
            //用户id
            stm.setInt(1, u_id);
            //执行命令
            rs = stm.executeQuery();

            while(rs.next()) {
                //实例化clock
                Clock clock = new Clock();
                //任务id
                clock.setClockId(rs.getInt("c_id"));
                //任务开始的时间
                clock.setClockStart(ControllerTime.Get_Date_Time(rs.getTimestamp("c_start")));
                //任务的时长
                clock.setClockDuration(ControllerTime.Get_Duration_Time(rs.getInt("c_duration")));
                if (rs.getTimestamp("c_end") != null) {
                    //任务结束的时间
                    clock.setClockEnd(ControllerTime.Get_Date_Time(rs.getTimestamp("c_end")));
                } else {
                    clock.setClockEnd("null");
                }
                //任务的标签
                clock.setClockLabel(rs.getString("c_label"));
                //任务的备注
                clock.setClockInfo(rs.getString("c_info"));
                //任务的完成状态
                clock.setClockFull(rs.getBoolean("c_full"));
                clocks.add(clock);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            //关闭数据库链接及执行命令
            rs.close();
            close(stm, conn);
        }

        return clocks;
    }
    //中断任务
    public Boolean Interrupt_Clock(int c_id) throws Exception {
        //创建数据库连接和命令对象
        Connection conn = DBBean.getConnection();
        Boolean result = false;
        String sql = "UPDATE tomato SET c_full=FALSE,c_end=NOW() WHERE tomato.c_id=?";
        PreparedStatement stm = null;
        int rs = 0;

        try {
            //查询语句
            stm = conn.prepareStatement(sql);
            //任务id
            stm.setInt(1, c_id);
            //执行命令
            rs = stm.executeUpdate();
            if (rs == 1){
                result = true;
            }else {
                result = false;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            close(stm, conn);
        }

        return result;
    }
    //完成任务
    public Boolean Finish_Clock(int c_id) throws Exception {
        //创建数据库连接和命令对象
        Connection conn = DBBean.getConnection();
        Boolean result = false;
        String sql = "update tomato set c_full = true,c_end = now() where  tomato.c_id=?";
        PreparedStatement stm = null;
        int rs = 0;
        try {
            //查询语句
            stm = conn.prepareStatement(sql);
            //任务id
            stm.setInt(1, c_id);
            //执行命令
            rs = stm.executeUpdate();
            if (rs == 1){
                result = true;
            }else {
                result = false;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            //关闭数据库链接及执行命令
            close(stm, conn);
        }
        return result;
    }
    //获得任务分析结果
    public Analysis Show_Analysis(int u_id) throws Exception {
        //创建数据库连接和命令对象
        Connection conn = DBBean.getConnection();
        Analysis analysis = null;
        List<Lable> todo_list = new ArrayList();
        String sum_sql = "CALL show_analysis(?)";
        String tomato_sql = "call show_analysis_clockList(?)";
        PreparedStatement Select_sum_stm = null;
        PreparedStatement Select_clock_stm = null;
        ResultSet sum_rs = null;
        ResultSet clock_rs = null;

        try {
            //查询语句
            Select_sum_stm = conn.prepareStatement(sum_sql);
            //存储过程
            Select_clock_stm = conn.prepareStatement(tomato_sql);
            //用户id
            Select_sum_stm.setInt(1, u_id);
            //用户id
            Select_clock_stm.setInt(1, u_id);
            //执行命令
            sum_rs = Select_sum_stm.executeQuery();
            clock_rs = Select_clock_stm.executeQuery();
            sum_rs.next();
            if (sum_rs.getInt(1) == 0) {
                todo_list.add(new Lable("学习", 0.0D));
                todo_list.add(new Lable("运动", 0.0D));
                todo_list.add(new Lable("工作", 0.0D));
                todo_list = Competer.Compere_List(todo_list);
                analysis = new Analysis(0, 0, 0, 0, todo_list);
            } else {
                while(clock_rs.next()) {
                    Lable label = new Lable(clock_rs.getString(1), ControllerTime.Get_Analysis_Scale_Duration_Time(clock_rs.getDouble(2)));
                    todo_list.add(label);
                }

                todo_list = Competer.Compere_List(todo_list);
                analysis = new Analysis(sum_rs.getInt(1), sum_rs.getInt(2) / 60, sum_rs.getInt(3), sum_rs.getInt(4) / 60, todo_list);
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }
        if (analysis.getTotalCount()==0 && analysis.getTotalDuration()==0){
            analysis.setIsGet(false);
        }else{
            analysis.setIsGet(true);
        }
        //关闭数据库链接及执行命令
        sum_rs.close();
        clock_rs.close();
        Select_clock_stm.close();
        Select_sum_stm.close();
        conn.close();
        return analysis;
    }

    public static void close(Statement stmt, Connection conn) {
        try {
            stmt.close();
            conn.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}
