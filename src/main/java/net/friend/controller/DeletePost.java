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
import main.java.net.friend.service.PostService;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class DeletePost extends HttpServlet {
    public DeletePost() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        //将前端的json对象进行解析
        int userId = user.getUserId();
        int postId = json.getInt("postId");
        PostService postService = new PostService();
        boolean result = postService.deletePost(postId);
        List<Map> list = new ArrayList<>();
        list = postService.selectPostByUserId(userId);
        Gson gson = new Gson();
        String str = gson.toJson(list);
        //返回json数据
        out.print("{\"isDelete\":" + result + ",\"message\":\"删除成功!\",\"discussMap\":"+str+"}");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
