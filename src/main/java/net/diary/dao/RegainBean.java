//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.tool.JdbcTool.DBBean;

public class RegainBean {
    private int id;
    private Connection conn;
    private boolean status;

    public RegainBean(int id) throws Exception {
        this.id = id;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }

    public boolean getStatus() {
        return this.status;
    }
    //查询指定日记
    public void checkExist() throws SQLException {
        String sql = "select d_id from diary where d_id = ?";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        ResultSet rs = pst.executeQuery();
        this.status = rs.next();
        //关闭命令
        rs.close();
        pst.close();
    }
    //还原指定日记
    public void regain() throws SQLException {
        String sql = "update diary set d_status = 1 where d_id = ? ";
        //更改语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        pst.executeUpdate();
        //关闭命令
        pst.close();
    }

    public String toJson() {
        return this.status ? "{\"isRegain\":true,\"message\":\"还原成功!\"," : "{\"isRegain\":false,\"message\":\"对不起！还原失败!\",";
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
