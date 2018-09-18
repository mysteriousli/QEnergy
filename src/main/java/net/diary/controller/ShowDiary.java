//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.net.diary.Tool;
import main.java.net.diary.dao.SearchBean;
import main.java.net.diary.exception.DiaryNotFondException;
import main.java.net.diary.exception.ParameterException;
import main.java.tool.JsonTool.JsonReader;
import net.sf.json.JSONObject;

public class ShowDiary extends HttpServlet {
    public ShowDiary() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取前端的json对象
        JSONObject jsonObject = JSONObject.fromObject(JsonReader.receivePost(request).toString());

        try {
            //将前端的json对象进行解析
            int id = jsonObject.getInt("id");
            Tool.checkParams(id, new String[]{"123"});
            //查找制定日记
            SearchBean searchBean = new SearchBean(id);
            searchBean.search();
            //返回json数据
            this.returnResult(response, searchBean.toJson());
        } catch (NumberFormatException | ParameterException var6) {
            var6.printStackTrace();
            response.setStatus(402);
        } catch (SQLException var7) {
            var7.printStackTrace();
            response.setStatus(403);
        } catch (DiaryNotFondException var8) {
            var8.printStackTrace();
            response.setStatus(404);
        } catch (Exception var9) {
            var9.printStackTrace();
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
