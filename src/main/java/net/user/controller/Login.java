//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.user.dao.UserDao;
import main.java.net.user.dao.UserDaoImpl;
import main.java.net.user.entity.User;
import main.java.net.user.service.UserLogin;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class Login extends HttpServlet {
    public Login() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session对象
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        //将前端的json对象进行解析
        JSONObject json = jsonObject.getJSONObject("userLogin");
        String message = "";
        String telNumber = json.getString("telNumber");
        String pwd = json.getString("pwd");
        //实例化用户登录类
        UserLogin userLogin = new UserLogin();
        //判断用户是否登录成功
        message = userLogin.telNumberIsExist(telNumber);
        if (message != null) {
            //返回json数据
            out.print("{\"isLogin\":false,\"message\":\"" + message + "\",\"userName\":\"\"}");
        } else {
            message = userLogin.pwdIsRight(pwd, telNumber);
            if (!message.equals("")) {
                out.print("{\"isLogin\":false,\"message\":\"" + message + "\"}");
            } else {
                //将登陆成功的用户信息保存到session对象中
                UserDao userDao = new UserDaoImpl();
                User user = userDao.selectUserByTelNumber(telNumber);
                session.setAttribute("user", user);
                message = "恭喜！登陆成功！";
                //返回json数据
                out.print("{\"isLogin\":true,\"message\":\"" + message + "\",\"userName\":\""+user.getUserName()+"\",\"avator\":\""+user.getAvator()+"\"}");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
