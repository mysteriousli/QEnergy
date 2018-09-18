package main.java.net.friend.controller;

import com.google.gson.Gson;
import main.java.net.friend.service.CommentService;
import main.java.net.friend.service.PostService;
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
import java.util.Map;

public class DeleteUserComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        String msg = "";
        int userId = user.getUserId();
        int commId = json.getInt("commId");
        CommentService commentService = new CommentService();
        if(commentService.deleteComment(commId)){

            PostService postService = new PostService();
            List<Map> list = new ArrayList<>();
            list = postService.selectPostAll(user.getUserId());
            Gson gson = new Gson();
            String str = gson.toJson(list);
            msg = "delete success";
            out.print("{\"msg\":\""+msg+"\",\"isDelete\":true,\"commentList\":"+commentService.selectAllByUserId(userId)+",\"discussMap\":"+str+"}");
            //实现返回json
        }else{
            PostService postService = new PostService();
            List<Map> list = new ArrayList<>();
            list = postService.selectPostAll(user.getUserId());
            Gson gson = new Gson();
            String str = gson.toJson(list);
            msg = "delete failure";
            out.print("{\"msg\":\""+msg+"\",\"isDelete\":true,\"commentList\":"+commentService.selectAllByUserId(userId)+",\"discussMap\":"+str+"}");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
