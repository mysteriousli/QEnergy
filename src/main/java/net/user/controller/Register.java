//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.user.dao.UserDao;
import main.java.net.user.dao.UserDaoImpl;
import main.java.net.user.entity.User;
import main.java.net.user.service.UserRegister;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        String message = "";
        //将前端的json对象进行解析
        JSONObject json = jsonObject.getJSONObject("userInfo");
        String userName = json.getString("userName");
        String telNumber = json.getString("telNumber");
        String realNumber = json.getString("realName");
        String pwd = json.getString("pwd");
        String idCard = json.getString("idCard");
        //实例化用户注册类
        UserRegister userRegister = new UserRegister();
        //判断用户是否注册成功
        message = userRegister.registerVerdict(telNumber);
        if (message != null) {
            //返回json数据
            out.print("{\"isRegister\":false,\"message\":\"" + message + "\",\"userName\":\""+userName+"\"}");
        } else {
            int avator = (int) (Math.random() * 5 + 1);
            User user = new User(-1, userName, pwd, telNumber, realNumber, idCard, "S");
            user.setAvator("http://120.78.86.45/avator/head"+String.valueOf(avator)+".jpg");
            if (userRegister.add(user)) {
                //将登陆成功的用户信息保存到session对象中
                UserDao userDao = new UserDaoImpl();
                session.setAttribute("user", userDao.selectUserByTelNumber(telNumber));
                message = "恭喜!注册成功！";
                //返回json数据
                out.print("{\"isRegister\":true,\"message\":\"" + message + "\",\"userName\":\""+userName+"\",\"avator\":\""+user.getAvator()+"\"}");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
