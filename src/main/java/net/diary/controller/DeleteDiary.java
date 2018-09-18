//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.net.diary.Tool;
import main.java.net.diary.dao.DeleteBean;
import main.java.net.diary.dao.ShowListBean;
import main.java.net.diary.exception.DiaryNotFondException;
import main.java.net.diary.exception.UnknownException;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class DeleteDiary extends HttpServlet {
    private static final String DATE_PATTERN = "yyyy-MM";

    public DeleteDiary() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象
        HttpSession session = request.getSession();
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());
        User user = (User)session.getAttribute("user");

        try {
            //将前端的json对象进行解析
            int userId = user.getUserId();
            String idStr = jsonObject.getString("id");
            String yearStr = jsonObject.getString("yearMonth");
            Date date = Tool.dateParser("yyyy-MM", yearStr);
            int[] dat = Tool.GetDate(date);
            Tool.checkParams(userId, new String[]{idStr, yearStr});
            int id = Integer.parseInt(idStr);
            //删除指定日记
            DeleteBean deleteBean = new DeleteBean(id);
            deleteBean.delete();
            //显示日记列表
            ShowListBean listBean = new ShowListBean(userId, dat[0], dat[1]);
            listBean.list();
            //返回json数据
            this.returnResult(response, listBean.toJson_tool(deleteBean.toJson()));
        } catch (NumberFormatException var14) {
            var14.printStackTrace();
            response.setStatus(402);
        } catch (SQLException var15) {
            var15.printStackTrace();
            response.setStatus(403);
        } catch (DiaryNotFondException var16) {
            var16.printStackTrace();
            response.setStatus(404);
        } catch (UnknownException var17) {
            var17.printStackTrace();
            response.setStatus(405);
        } catch (Exception var18) {
            var18.printStackTrace();
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
