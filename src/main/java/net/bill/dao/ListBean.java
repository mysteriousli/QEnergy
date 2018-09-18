//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import main.java.net.bill.Tool;
import main.java.net.bill.entity.Bill;
import main.java.net.bill.entity.DailyList;
import main.java.tool.JdbcTool.DBBean;

public class ListBean {
    private int userId;
    private int year;
    private int month;
    private double totalIncome;
    private double totalPay;
    private Connection conn;
    private LinkedHashMap<String, DailyList> lists;
    private static final String YEAR_PATTERN = "0000";
    private static final String MONEY_PATTERN = "0.00";
    private static final String MONTH_PATTERN = "00";

    public ListBean(int userId, int year, int month) throws Exception {
        this.userId = userId;
        this.year = year;
        this.month = month;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
        this.lists = new LinkedHashMap();
    }
    //获取账单列表
    public void list() throws SQLException {
        String bill_sql = "select date_format(b_date,'%Y-%m-%d') day,b_id,u_id,b_date,b_label,IconCode,b_type,b_money from bill where u_id = ? and date_format(b_date,'%Y-%m') = ? order by b_date desc";
        String send_sql = "select sum(b_money) from bill where u_id = ? and date_format(b_date,'%Y-%m') = ? and b_type=\"支出\"";
        String come_sql = "select sum(b_money) from bill where u_id = ? and date_format(b_date,'%Y-%m') = ? and b_type=\"收入\"";
        //查询语句
        PreparedStatement bill_pst = this.conn.prepareStatement(bill_sql);
        //查询语句
        PreparedStatement send_pst = this.conn.prepareStatement(send_sql);
        //查询语句
        PreparedStatement come_pst = this.conn.prepareStatement(come_sql);
        String filter = Tool.numberFormat("0000", this.year) + '-' + Tool.numberFormat("00", this.month);
        bill_pst.setInt(1, this.userId);
        bill_pst.setString(2, filter);
        come_pst.setInt(1, this.userId);
        come_pst.setString(2, filter);
        send_pst.setInt(1, this.userId);
        send_pst.setString(2, filter);
        //执行命令
        ResultSet rs = bill_pst.executeQuery();
        while(rs.next()) {
            String day = rs.getString(1);
            int id = rs.getInt(2);
            int userId = rs.getInt(3);
            Date date = new Date(rs.getTimestamp(4).getTime());
            String label = rs.getString(5);
            String IconCode = rs.getString(6);
            String type = rs.getString(7);
            double money = rs.getDouble(8);
            Bill bill = new Bill(id, userId, date, label, IconCode, type, money);
            DailyList tmp = this.lists.containsKey(day) ? (DailyList)this.lists.get(day) : new DailyList();
            tmp.addBill(bill);
            this.lists.put(day, tmp);
        }
        //执行命令
        ResultSet come_rs = come_pst.executeQuery();
        come_rs.next();
        this.totalIncome = come_rs.getDouble(1);
        //执行命令
        ResultSet send_rs = send_pst.executeQuery();
        send_rs.next();
        this.totalPay = send_rs.getDouble(1);
        //关闭数据库链接及执行命令
        rs.close();
        come_rs.close();
        send_rs.close();
        bill_pst.close();
        send_pst.close();
        come_pst.close();
        this.conn.close();
    }
    //将数据打包成json数据格式
    public String toJson() {
        StringBuilder buff = new StringBuilder();
        boolean flag = true;
        buff.append("{\"totalIncome\":").append("\"" + Tool.numberFormat("0.00", this.totalIncome) + "\"").append(',');
        buff.append("\"totalPay\":").append("\"" + Tool.numberFormat("0.00", this.totalPay) + "\"").append(',');
        buff.append("\"billList\":");
        buff.append('[');
        Iterator var3 = this.lists.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, DailyList> entry = (Entry)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(',');
            }

            buff.append('{');
            buff.append("\"dateFull\":\"").append((String)entry.getKey()).append("\",");
            buff.append("\"dayDetail\":").append(((DailyList)entry.getValue()).toJsonArray());
            buff.append('}');
        }

        buff.append(']');
        buff.append('}');
        return buff.toString();
    }
}
