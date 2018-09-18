//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.net.diary.entity.Diary;
import main.java.net.diary.exception.UnknownException;
import main.java.tool.JdbcTool.DBBean;

public class CreateBean {
    private Diary diary;
    private Connection conn;

    public CreateBean(Diary diary) throws Exception {
        this.diary = diary;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }
    //创建日记
    public void create() throws SQLException, UnknownException {
        String sql = "insert into diary(d_date, d_content, d_weather, d_mood, u_id) VALUE (now(),?,?,?,?)";
        //插入语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setString(1, this.diary.getContent());
        pst.setString(2, this.diary.getWeather());
        pst.setString(3, this.diary.getMood());
        pst.setInt(4, this.diary.getUserId());
        //执行命令
        pst.executeUpdate();
        //关闭命令
        pst.close();
    }

    public String toJson() {
        String str = null;
        if (String.valueOf(this.diary.getId()) == null) {
            str = "{\"isCreated\":false,\"message\":\"对不起！保存失败！\",";
        } else {
            str = "{\"isCreated\":true,\"message\":\"恭喜！保存成功！\",";
        }

        return str;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
