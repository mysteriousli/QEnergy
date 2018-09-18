package main.java.net.friend.controller;

import com.google.gson.Gson;
import main.java.net.friend.entity.Comment;
import main.java.net.friend.entity.Post;
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

public class Transpond extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject json = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        String msg = "";

        int postId = json.getInt("postId");//被转帖子id
        int userId = user.getUserId();//转发用户id
        String postContent = json.getString("postContent");//帖子内容
        String commContent = json.getString("commContent");//评论内容
        PostService postService = new PostService();
        //评论
        Comment comment = new Comment();
        comment.setCommText(commContent);
        comment.setCommId(userId);
        comment.setCommPostId(postId);
        CommentService commentService = new CommentService();
        commentService.commentMsg(comment);
        //合成新帖子内容   被转发帖子的用户的昵称+帖子内容
        postContent = commContent+postContent+":"+postService.getUserName(postId);
        //发布新帖子
        Post post = new Post();
        post.setId(-1);
        post.setFabCount(0);
        post.setText(postContent);
        //转发并评论
        if(postService.PostMsg(post,userId)!=-1){
            List<Map> list = new ArrayList<>();
            list = postService.selectPostAll(userId);
            Gson gson = new Gson();
            String str = gson.toJson(list);
            msg = "transpond success";
            out.print("{\"msg\":\""+msg+"\",\"id\":"+commentService.selectNewestFirst()+",\"discussMap\":"+str+"}");
        }else{
            msg = "transpond failure";
            out.print("{\"msg\":\""+msg+"\",\"id\":null,\"postList\":null}");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
