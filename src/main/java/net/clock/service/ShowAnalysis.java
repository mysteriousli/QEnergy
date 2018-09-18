//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.clock.dao.ClockDaoImpI;
import main.java.net.clock.entity.Analysis;
import main.java.net.user.entity.User;

public class ShowAnalysis extends HttpServlet {
    public ShowAnalysis() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取用户
        User user = (User)session.getAttribute("user");
        Analysis analysis = null;
        ClockDaoImpI clockDaoImpI = new ClockDaoImpI();

        try {
            analysis = clockDaoImpI.Show_Analysis(user.getUserId());
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        Gson gson = new Gson();
        String str = gson.toJson(analysis);
        PrintWriter out = response.getWriter();
        //返回json数据
        out.print(str);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
