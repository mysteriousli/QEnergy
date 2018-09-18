//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import main.java.net.bill.Tool;
import main.java.net.diary.entity.Diary;
import main.java.tool.JdbcTool.DBBean;
import org.apache.commons.lang.StringEscapeUtils;

public class ShowListBean {
    private int userId;
    private int year;
    private int month;
    private Connection conn;
    private ArrayList<Diary> diaries;
    private static final String YEAR_PATTERN = "0000";
    private static final String MONTH_PATTERN = "00";

    public ShowListBean(int userId, int year, int month) throws Exception {
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.diaries = new ArrayList();
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }

    public ShowListBean(int userId) throws Exception {
        this.userId = userId;
        this.diaries = new ArrayList();
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }
    //显示未删除日记列表
    public void list() throws SQLException {
        String sql = "select d_id,d_date,d_content,d_mood,d_weather from diary where u_id = ? and d_status = 1 and date_format(d_date,'%Y-%m') = ? order by d_date desc";
        String filter = Tool.numberFormat("0000", this.year) + '-' + Tool.numberFormat("00", this.month);
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.userId);
        pst.setString(2, filter);
        //执行命令
        ResultSet rs = pst.executeQuery();

        while(rs.next()) {
            this.diaries.add(new Diary(rs.getInt(1), new Date(rs.getTimestamp(2).getTime()), StringEscapeUtils.unescapeHtml(rs.getString(3)), rs.getString(4), rs.getString(5)));
        }
        //关闭命令
        rs.close();
        pst.close();
    }

    public void list_deleted() throws SQLException {
        String sql = "select d_id,d_date,d_content,d_mood,d_weather from diary where u_id = ? and d_status = 0 order by d_date desc";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.userId);
        //执行命令
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            this.diaries.add(new Diary(rs.getInt(1), new Date(rs.getTimestamp(2).getTime()), StringEscapeUtils.unescapeHtml(rs.getString(3)), rs.getString(4), rs.getString(5)));
        }
        //关闭命令
        rs.close();
        pst.close();
    }

    public String toJson() throws IOException {
        StringBuilder buff = new StringBuilder("{\"diaryList\":[");
        boolean flag = true;

        Diary diary;
        for(Iterator var3 = this.diaries.iterator(); var3.hasNext(); buff.append(diary.toListJson())) {
            diary = (Diary)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(",");
            }
        }

        buff.append("]}");
        return buff.toString();
    }

    public String toJson_tool(String result) throws IOException {
        StringBuilder buff = new StringBuilder(result + "\"diaryList\":[");
        boolean flag = true;

        Diary diary;
        for(Iterator var4 = this.diaries.iterator(); var4.hasNext(); buff.append(diary.toListJson())) {
            diary = (Diary)var4.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(",");
            }
        }

        buff.append("]}");
        return buff.toString();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
