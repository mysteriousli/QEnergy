//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.controller;

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

import com.google.gson.Gson;
import main.java.net.friend.service.LikeService;
import main.java.net.friend.service.PostService;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class Like extends HttpServlet {
    public Like() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        User user = (User)session.getAttribute("user");
        //将前端的json对象进行解析
        int postId = json.getInt("postId");
        int userId = user.getUserId();
        LikeService likeService = new LikeService();
        boolean re = likeService.likePost(userId, postId);
        PostService postService = new PostService();
        List<Map> list = new ArrayList<>();
        list = postService.selectPostAll(userId);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        //返回json数据
        out.print("{\"isCancel\":" + re + ",\"like\":" + postService.selectFabCount(postId) + ",\"discussMap\":"+str+"}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
