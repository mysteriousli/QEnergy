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

public class DeleteBean {
    private int id;
    private Connection conn;
    private boolean status;

    public DeleteBean(int id) throws Exception {
        this.id = id;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }

    public boolean getStatus() {
        return this.status;
    }
    //查找该日记是否存在
    public void checkExist() throws SQLException {
        String sql = "select d_id from diary where d_id = ?;";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        ResultSet rs = pst.executeQuery();
        this.status = !rs.next();
        //关闭命令
        rs.close();
        pst.close();
    }
    //删除制定日记
    public void delete() throws SQLException {
        this.status = true;
        String sql = "update diary set d_status = 0 where d_id = ? ";
        //更改语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        pst.executeUpdate();
        //关闭命令
        pst.close();
    }
    //彻底删除制定日记
    public void deleteInch() throws SQLException {
        String sql = "delete from diary where d_id = ? ";
        //删除语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setInt(1, this.id);
        //执行命令
        pst.executeUpdate();
        //关闭命令
        pst.close();
    }

    public String toJson() {
        return this.status ? "{\"isDelete\":true,\"message\":\"删除成功!\"," : "{\"isDelete\":false,\"message\":\"对不起！删除失败!\",";
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.conn.close();
    }
}
