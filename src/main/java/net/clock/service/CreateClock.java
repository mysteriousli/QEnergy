//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.clock.dao.ClockDaoImpI;
import main.java.net.clock.entity.Clock;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class CreateClock extends HttpServlet {
    public CreateClock() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        //将前端的json对象进行解析
        Clock clock = new Clock();
        clock.setClockLabel(jsonObject.getString("clockLabel"));
        clock.setClockInfo(jsonObject.getString("clockInfo"));
        clock.setClockDuration(String.valueOf(jsonObject.getInt("clockDuration") / 1000));
        User user = (User)session.getAttribute("user");
        //实例化任务类
        ClockDaoImpI clockDaoImpI = new ClockDaoImpI();
        int result = -1;

        try {
            //创建任务并返回任务id
            result = clockDaoImpI.Create_Clock(clock, user.getUserId());
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        //将任务id保存到session对象中
        session.setAttribute("clockId", result);
        if (result == -1) {
            //返回json数据
            out.print("{\"message\":\"创建失败!\"}");
            out.flush();
            out.close();
        } else {
            //返回json数据
            out.print("{\"message\":\"创建成功!\"}");
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
