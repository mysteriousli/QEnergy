//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.net.diary.entity.Diary;
import main.java.tool.JdbcTool.DBBean;

public class EditBean {
    private Connection conn = DBBean.getConnection();
    private Diary diary;
    private String weather;
    private String mood;
    private String content;

    public EditBean(Diary diary, String weather, String mood, String content) throws Exception {
        this.diary = diary;
        this.weather = weather;
        this.mood = mood;
        this.content = content;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public boolean isSame() {
        return this.diary.getWeather().equals(this.weather) && this.diary.getMood().equals(this.mood) && this.diary.getContent().equals(this.content);
    }
    //编辑指定日记
    public void edit() throws SQLException {
        String sql = "update diary set d_weather = ? , d_mood = ? , d_content = ? where d_id = ?;";
        //更改语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setString(1, this.weather);
        pst.setString(2, this.mood);
        pst.setString(3, this.content);
        pst.setInt(4, this.diary.getId());
        //执行命令
        pst.executeUpdate();
        //关闭命令
        pst.close();
    }

    public String toJson() {
        return "{\"isChange\":" + this.isSame() + "\"message\":\"恭喜！修改成功\",";
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
