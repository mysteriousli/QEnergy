//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import main.java.net.friend.entity.Post;
import main.java.net.friend.entity.PostM;
import main.java.net.friend.service.PostService;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

public class PostMsg extends HttpServlet {
    public PostMsg() {
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
        String content = json.getString("content");
        content = StringEscapeUtils.escapeHtml(content);
        content = content.replaceAll("\n","<br>");
        String msg = "";
        Post post = new Post();
        post.setId(-1);
        post.setFabCount(0);
        post.setText(content);
        SimpleDateFormat df = null;
        PostService postService = new PostService();
        if (content!=null) {
            int id = postService.PostMsg(post, userId);
            List<Map> list = new ArrayList<>();
            list = postService.selectPostAll(userId);
            PostM postM = null;
            if (id != 0) {
                msg = "恭喜！发帖成功！";
                postM = new PostM(id, msg, list);
            } else {
                msg = "对不起！发帖失败！";
                postM = new PostM(-1, msg, list);
            }
            Gson gson = new Gson();
            String str = gson.toJson(postM);
            //返回json数据
            out.print(str);
            out.flush();
            out.close();
        }else {
            List<Map> list = new ArrayList<>();
            list = postService.selectPostAll(userId);
            PostM postM = null;
            msg = "对不起！发帖失败！";
            postM = new PostM(-1, msg, list);
            Gson gson = new Gson();
            String str = gson.toJson(postM);
            //返回json数据
            out.print(str);
            out.flush();
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
