//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.net.diary.entity.Diary;
import main.java.net.diary.exception.DiaryNotFondException;
import main.java.tool.JdbcTool.DBBean;
import org.apache.commons.lang.StringEscapeUtils;

public class SearchBean {
    private int id;
    private Connection conn;
    private Diary diary;

    public SearchBean(int id) throws Exception {
        this.id = id;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }
    //查询指定日记
    public void search() throws SQLException, DiaryNotFondException {
        String sql = "select d_id,u_id,d_date,d_weather,d_mood,d_content from diary where d_id = ?";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            //关闭命令
            this.diary = new Diary(rs.getInt(1), rs.getInt(2), new Date(rs.getTimestamp(3).getTime()), rs.getString(4), rs.getString(5), StringEscapeUtils.unescapeHtml(rs.getString(6)));
            rs.close();
            pst.close();
        } else {
            throw new DiaryNotFondException();
        }
    }

    public Diary getDiary() {
        return this.diary;
    }

    public String toJson() throws IOException {
        return this.diary.toJson();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
