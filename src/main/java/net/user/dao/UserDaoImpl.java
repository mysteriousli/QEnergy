//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.net.clock.controller.ControllerTime;
import main.java.net.user.entity.User;
import main.java.tool.JdbcTool.DBBean;

public class UserDaoImpl implements UserDao {
    public UserDaoImpl() {
    }
    //通过手机号码进行用户的查找
    public User selectUserByTelNumber(String telNumber) {
        User user = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean var6 = false;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select * from user where u_tel = ?";
            ps = conn.prepareStatement(sql);
            //电话号
            ps.setString(1, telNumber);
            //执行命令
            rs = ps.executeQuery();
            if (rs.next()) {
                user = this.setUser(user, rs);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            //关闭数据库连接和命令对象
            DBBean.close(ps, conn);
        }

        return user;
    }
    //向数据库中插入用户
    public boolean insert(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        Integer oldCount = this.selectLast();

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "insert INTO user(u_password,u_tel,u_state,u_nick_name,u_time,u_real_name,u_id_card,avator) VALUES (?,?,?,?,now(),?,?,?);";
            ps = conn.prepareStatement(sql);
            //用户密码
            ps.setString(1, user.getPwd());
            //用户电话号码
            ps.setString(2, user.getTelNumber());
            //用户的状态
            ps.setString(3, user.getState());
            //用户的昵称
            ps.setString(4, user.getUserName());
            //用户的真实姓名
            ps.setString(5, user.getRealName());
            //用户的身份证号码
            ps.setString(6, user.getIdCard());
            //用户头像
            ps.setString(7,user.getAvator());
            //关闭数据库连接和命令对象
            ps.executeUpdate();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            //关闭数据库连接及操作命令
            DBBean.close(ps, conn);
        }
        //查找当前用户id
        Integer newCount = this.selectLast();
        return oldCount < newCount;
    }
    //查询用户的id
    public int selectLast() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastId = -1;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "SELECT u_id FROM user ORDER BY u_id DESC LIMIT 1";
            //查询语句
            ps = conn.prepareStatement(sql);
            //执行命令
            rs = ps.executeQuery();
            if (rs.next()) {
                //获得id
                lastId = rs.getInt(1);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            DBBean.close(ps, conn);
        }
        return lastId;
    }

    @Override
    public User selectUserByUserId(int userId) {
        User user = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `user` where u_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            if(rs.next()){
                user = setUser(user,rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return user;
    }

    //注入用户类型
    public User setUser(User user, ResultSet rs) throws SQLException {
        System.out.print(rs.getInt(1));
        user.setUserId(rs.getInt(1));
        user.setPwd(rs.getString(2));
        user.setTelNumber(rs.getString(3));
        user.setState(rs.getString(4));
        user.setUserName(rs.getString(5));
        user.setDateTime(ControllerTime.Get_Date_Time(rs.getTimestamp(6)));
        user.setRealName(rs.getString(7));
        user.setIdCard(rs.getString(8));
        user.setAvator(rs.getString(9));
        return user;
    }
}
