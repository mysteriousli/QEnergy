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

public class InterruptClock extends HttpServlet {
    public InterruptClock() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获得任务id
        int clockId = (Integer)session.getAttribute("clockId");
        ClockDaoImpI clockDaoImpI = new ClockDaoImpI();
        Boolean isInterrupt = false;

        try {
            isInterrupt = clockDaoImpI.Interrupt_Clock(clockId);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        Gson gson = new Gson();
        String str = gson.toJson(isInterrupt);
        //返回json数据
        out.print("{\"isInterrupt\":" + str + "}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
