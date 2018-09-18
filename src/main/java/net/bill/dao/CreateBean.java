//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import main.java.net.bill.entity.Bill;
import main.java.net.bill.exception.UnknownException;
import main.java.tool.JdbcTool.DBBean;

public class CreateBean {
    private Connection conn;
    private Bill bill;

    public CreateBean(Bill bill) throws Exception {
        this.bill = bill;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
    }
    //创作账单
    public void create() throws SQLException, UnknownException {
        String sql = "insert into bill(b_label, iconCode, b_money, b_date, b_type, u_id) value (?,?,?,?,?,?);";
        //插入语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        pst.setString(1, this.bill.getLabel());
        pst.setString(2, this.bill.getIconCode());
        pst.setDouble(3, this.bill.getMoney());
        pst.setTimestamp(4, new Timestamp(this.bill.getDate().getTime()));
        pst.setString(5, this.bill.getType());
        pst.setInt(6, this.bill.getUserId());
        //执行命令
        pst.executeUpdate();
        sql = "select LAST_INSERT_ID()";
        //查询语句
        pst = this.conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            this.bill.setId(rs.getInt(1));
            rs.close();
            pst.close();
            this.conn.close();
        } else {
            throw new UnknownException();
        }
    }
    //将数据信息打包成json数据
    public String toJson() {
        return "{\"id\":" + this.bill.getId() + "}";
    }
}
