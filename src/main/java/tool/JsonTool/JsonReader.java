//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.tool.JsonTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public class JsonReader {
    public JsonReader() {
    }
    //获取前端的基本数据
    public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();

        while((line = br.readLine()) != null) {
            sb.append(line);
        }

        JSONObject json = JSONObject.fromObject(sb.toString());
        return json;
    }
}
