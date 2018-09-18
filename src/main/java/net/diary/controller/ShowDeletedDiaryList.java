//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.diary.Tool;
import main.java.net.diary.dao.ShowListBean;
import main.java.net.diary.exception.ParameterException;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

@WebServlet(
        name = "ShowDeletedDiaryList"
)
public class ShowDeletedDiaryList extends HttpServlet {
    public ShowDeletedDiaryList() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象
        HttpSession session = request.getSession();
        try {
            User user = (User)session.getAttribute("user");
            int userId = user.getUserId();
            Tool.checkParams(userId, new String[]{"12"});
            //显示日记列表
            ShowListBean listBean = new ShowListBean(userId);
            listBean.list_deleted();
            //返回json数据
            this.returnResult(response, listBean.toJson());
        } catch (ParameterException var8) {
            var8.printStackTrace();
            response.setStatus(402);
        } catch (SQLException var9) {
            var9.printStackTrace();
            response.setStatus(403);
        } catch (Exception var10) {
            var10.printStackTrace();
            response.setStatus(400);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    private void returnResult(HttpServletResponse response, String json) {
        try {
            PrintWriter out = response.getWriter();
            Throwable var4 = null;

            try {
                out.print(json);
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (IOException var16) {
            var16.printStackTrace();
        }

    }
}
