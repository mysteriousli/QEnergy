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
import main.java.net.diary.dao.CreateBean;
import main.java.net.diary.dao.ShowListBean;
import main.java.net.diary.entity.Diary;
import main.java.net.diary.exception.ParameterException;
import main.java.net.user.entity.User;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

public class CreateDiary extends HttpServlet {
    private static final String DATE_PATTERN = "yyyy-MM";

    public CreateDiary() {
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
            String weather = jsonObject.getString("weather");
            String mood = jsonObject.getString("mood");
            String content = jsonObject.getString("content");
            String yearStr = jsonObject.getString("yearMonth");
            Tool.checkParams(userId, new String[]{weather, mood, content, yearStr});
            Date date = Tool.dateParser("yyyy-MM", yearStr);
            int[] dat = Tool.GetDate(date);
            content = StringEscapeUtils.escapeHtml(content);
            content = content.replaceAll("\n","<br>");
            Diary diary = new Diary(userId, weather, mood, content);
            //创建日记
            CreateBean createBean = new CreateBean(diary);
            createBean.create();
            //按月获取日记列表
            ShowListBean listBean = new ShowListBean(userId, dat[0], dat[1]);
            listBean.list();
            //返回json数据
            this.returnResult(response, listBean.toJson_tool(createBean.toJson()));
        } catch (ParameterException var16) {
            var16.printStackTrace();
            response.setStatus(402);
        } catch (SQLException var17) {
            var17.printStackTrace();
            response.setStatus(403);
        } catch (UnknownError var18) {
            var18.printStackTrace();
            response.setStatus(405);
        } catch (Exception var19) {
            var19.printStackTrace();
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
                out.flush();
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
