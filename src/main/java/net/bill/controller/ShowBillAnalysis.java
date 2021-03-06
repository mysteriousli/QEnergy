//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.bill.Tool;
import main.java.net.bill.dao.AnalysisBean;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

@WebServlet(
        name = "ShowBillAnalysis"
)
public class ShowBillAnalysis extends HttpServlet {
    private static final String DATE_PATTERN = "yyyy-MM";

    public ShowBillAnalysis() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        User user = (User)session.getAttribute("user");

        try {
            //解析数据
            int userId = user.getUserId();
            String yearStr = jsonObject.getString("yearMonth");
            Tool.checkParams(userId, new String[]{yearStr});
            Date date = Tool.dateParser(DATE_PATTERN, yearStr);
            int[] dat = Tool.GetDate(date);
            //按月分析账单
            AnalysisBean analysisBean = new AnalysisBean(userId, dat[0], dat[1]);
            analysisBean.analysis();
            //返回json数据
            this.returnResult(response, analysisBean.toJson());
        } catch (NumberFormatException var11) {
            var11.printStackTrace();
            response.setStatus(402);
        } catch (SQLException var12) {
            var12.printStackTrace();
            response.setStatus(403);
        } catch (Exception var13) {
            var13.printStackTrace();
            response.setStatus(400);
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
