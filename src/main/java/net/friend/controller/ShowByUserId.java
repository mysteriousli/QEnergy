//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.friend.entity.Post;
import main.java.net.friend.service.PostService;
import main.java.net.user.entity.User;

public class ShowByUserId extends HttpServlet {
    public ShowByUserId() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        new ArrayList();
        PostService postService = new PostService();
        //根据用户id查询讨论列表
        List<Map> list = new ArrayList<>();
        list = postService.selectPostByUserId(userId);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        //返回json数据
        out.print("{\"discussMap\":" + str + "}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
