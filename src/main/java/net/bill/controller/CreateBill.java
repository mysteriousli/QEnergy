
package main.java.net.bill.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.bill.Tool;
import main.java.net.bill.dao.CreateBean;
import main.java.net.bill.entity.Bill;
import main.java.net.bill.exception.ParameterException;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;
import org.omg.CORBA.portable.UnknownException;

public class CreateBill extends HttpServlet {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public CreateBill() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        //获得用户
        User user = (User)session.getAttribute("user");

        try {
            //将前端的json对象进行解析
            int userId = user.getUserId();
            String dateStr = jsonObject.getString("date");
            String label = jsonObject.getString("label");
            String iconCode = jsonObject.getString("iconCode");
            String type = jsonObject.getString("type");
            String moneyStr = jsonObject.getString("money");
            Tool.checkParams(userId, new String[]{dateStr, label, iconCode, type, moneyStr});
            Date date = Tool.dateParser1("yyyy-MM-dd HH:mm:ss", dateStr);
            Tool.checkBillType(type);
            double money = Double.parseDouble(moneyStr);
            if (money <= 0.0D) {
                throw new ParameterException();
            }

            Bill bill = new Bill(userId, date, label, iconCode, type, money);
            //创建账单
            CreateBean createBean = new CreateBean(bill);
            createBean.create();
            //返回json数据
            this.returnResult(response, createBean.toJson());
        } catch (NumberFormatException | ParameterException var17) {
            var17.printStackTrace();
            response.sendError(402);
        } catch (SQLException var18) {
            var18.printStackTrace();
            response.sendError(403);
        } catch (UnknownException var19) {
            var19.printStackTrace();
            response.sendError(405);
        } catch (Exception var20) {
            var20.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    private void returnResult(HttpServletResponse response, String json) {
        try {
            PrintWriter out = response.getWriter();
            Throwable var4 = null;

            try {
                out.print(json);
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (IOException var16) {
            var16.printStackTrace();
        }

    }
}
