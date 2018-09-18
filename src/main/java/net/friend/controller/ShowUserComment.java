package main.java.net.friend.controller;

import com.google.gson.Gson;
import main.java.net.friend.entity.Comment;
import main.java.net.friend.service.CommentService;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowUserComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());

        List<Comment> list = new ArrayList<Comment>();
        int userId = user.getUserId();
        CommentService commentService = new CommentService();
        list = commentService.selectAllByUserId(userId);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        out.print("\"commentList\":"+str);
        //实现返回json
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
