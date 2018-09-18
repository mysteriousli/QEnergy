//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package main.java.net.bill.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import main.java.net.bill.Tool;
import main.java.net.bill.entity.DailyAnalysis;
import main.java.net.bill.exception.ParameterException;
import main.java.tool.JdbcTool.DBBean;
public class AnalysisBean {
    private int userId;
    private int year;
    private int month;
    private Connection conn;
    private LinkedHashMap<String, Double> tagPays;
    private LinkedHashMap<String, Double> tagIncomes;
    private LinkedHashMap<String, DailyAnalysis> dailyAnalysis;
    private boolean isGet;
    public AnalysisBean(int userId, int year, int month) throws Exception {
        this.userId = userId;
        this.year = year;
        this.month = month;
        //创建数据库连接和命令对象
        this.conn = DBBean.getConnection();
        this.tagPays = new LinkedHashMap();
        this.tagIncomes = new LinkedHashMap();
        this.dailyAnalysis = new LinkedHashMap();
    }
    public void analysis() throws SQLException, ParameterException {
        this.pieAnalysis();
        this.lineAnalysis();
    }
    //按月获取分析结果
    private void pieAnalysis() throws SQLException {
        String sql = "select b_label,sum(b_money) from bill where u_id = ? and date_format(b_date,'%Y-%m') = ? and b_type = ? group by b_label order by b_label;";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        String filter = Tool.numberFormat("0000", this.year) + '-' + Tool.numberFormat("00", this.month);
        pst.setInt(1, this.userId);
        pst.setString(2, filter);
        pst.setString(3, "支出");
        //执行命令
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            this.tagPays.put(rs.getString(1), rs.getDouble(2));
        }
        pst.setString(3, "收入");
        //执行命令
        rs = pst.executeQuery();
        while(rs.next()) {
            this.tagIncomes.put(rs.getString(1), rs.getDouble(2));
        }
        //关闭数据库链接及执行命令
        rs.close();
        pst.close();
    }
    //按月获取前书
    private void lineAnalysis() throws SQLException, ParameterException {
        String sql = "select date_format(b_date,'%Y-%m-%d') day,b_type,sum(b_money) from bill where u_id = ? and date_format(b_date,'%Y-%m') = ? group by day,b_type;";
        //查询语句
        PreparedStatement pst = this.conn.prepareStatement(sql);
        String filter = Tool.numberFormat("0000", this.year) + '-' + Tool.numberFormat("00", this.month);
        pst.setInt(1, this.userId);
        pst.setString(2, filter);
        //执行命令
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            String day = rs.getString(1);
            double money = (double)Tool.checkBillType(rs.getString(2)) * rs.getDouble(3);
            DailyAnalysis tmp;
            if (this.dailyAnalysis.containsKey(day)) {
                tmp = (DailyAnalysis)this.dailyAnalysis.get(day);
            } else {
                tmp = new DailyAnalysis();
            }
            tmp.record(money);
            this.dailyAnalysis.put(day, tmp);
        }
        //关闭数据库链接及执行命令
        rs.close();
        pst.close();
    }
    //将信息打包成json格式数据
    public String toJson() {
        if (tagPays.entrySet().iterator().hasNext()||tagIncomes.entrySet().iterator().hasNext()||dailyAnalysis.entrySet().iterator().hasNext()){
            isGet = true;
        }else{
            isGet = false;
        }
        StringBuilder buff = new StringBuilder("{");
        boolean flag = true;
        buff.append("\"pieList\":{");
        buff.append("\"labelPayList\":[");
        Iterator var3 = this.tagPays.entrySet().iterator();

        Entry entry;
        while(var3.hasNext()) {
            entry = (Entry)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(',');
            }

            buff.append('{');
            buff.append("\"payLabel\":\"").append((String)entry.getKey()).append("\",");
            buff.append("\"payTotal\":").append(Tool.numberFormat("0.00", (Double)entry.getValue()));
            buff.append('}');
        }

        buff.append("],");
        buff.append("\"labelIncomeList\":[");
        flag = true;
        var3 = this.tagIncomes.entrySet().iterator();

        while(var3.hasNext()) {
            entry = (Entry)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(',');
            }

            buff.append("{");
            buff.append("\"incomeLabel\":\"").append((String)entry.getKey()).append("\",");
            buff.append("\"incomeTotal\":").append("\"" + Tool.numberFormat("0.00", (Double)entry.getValue()) + "\"");
            buff.append("}");
        }

        buff.append("]");
        buff.append("},");
        buff.append("\"lineList\":[");
        flag = true;
        var3 = this.dailyAnalysis.entrySet().iterator();

        while(var3.hasNext()) {
            entry = (Entry)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(',');
            }

            buff.append("{");
            buff.append("\"day\":\"").append((String)entry.getKey()).append("\",");
            buff.append("\"dayIncome\":").append("\"" + Tool.numberFormat("0.00", ((DailyAnalysis)entry.getValue()).getIncome()) + "\"").append(',');
            buff.append("\"dayPay\":").append("\"" + Tool.numberFormat("0.00", ((DailyAnalysis)entry.getValue()).getPay()) + "\"");
            buff.append("}");
        }

        buff.append("],");
        buff.append("\"isGet\":").append(isGet);
        buff.append("}");
        return buff.toString();
    }
}
