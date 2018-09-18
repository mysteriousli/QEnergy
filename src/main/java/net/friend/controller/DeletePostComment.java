package main.java.net.friend.controller;

import main.java.net.friend.service.CommentService;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DeletePostComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        String msg = "";
        int commId = json.getInt("commId");
        CommentService commentService = new CommentService();
        if(commentService.deleteComment(commId)){
            msg = "delete success";
            out.print("{\"msg\":\""+msg+"\",\"isDelete\":true,\"commentList\":"+commentService.selectNewestFirst()+"}");
            //实现返回json
        }else{
            msg = "delete failure";
            out.print("{\"msg\":\""+msg+"\",\"isDelete\":false,\"commentList\":"+commentService.selectNewestFirst()+"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
