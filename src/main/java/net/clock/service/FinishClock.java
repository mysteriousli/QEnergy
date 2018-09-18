package main.java.net.clock.service;

import com.google.gson.Gson;
import main.java.net.clock.dao.ClockDaoImpI;
import main.java.net.user.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "FinishClock")
public class FinishClock extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获得任务id
        int clockId = (Integer)session.getAttribute("clockId");
        User user = (User) session.getAttribute("user");
        ClockDaoImpI clockDaoImpI = new ClockDaoImpI();
        Boolean isFinish = false;
        Object clockList = new ArrayList();

        try {
            isFinish = clockDaoImpI.Finish_Clock(clockId);
            clockList = clockDaoImpI.Show_TodoList(user.getUserId());
        } catch (Exception var10) {
            var10.printStackTrace();
        }
        Gson gson = new Gson();
        String str = gson.toJson(isFinish);
        String list = gson.toJson(clockList);
        //返回json数据
        out.print("{\"isFinish\":" + str + ",\"clockList\":" + list + "}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
