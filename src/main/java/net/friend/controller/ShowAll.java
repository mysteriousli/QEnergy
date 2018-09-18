//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ShowAll extends HttpServlet {
    public ShowAll() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        new ArrayList();
        PostService postService = new PostService();
        int userId = user.getUserId();
        List<Map> map = new ArrayList<>();
        map = postService.selectPostAll(userId);
        Gson gson = new Gson();
        String str = gson.toJson(map);
        out.print("{\"discussMap\":" + str
                + "}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
