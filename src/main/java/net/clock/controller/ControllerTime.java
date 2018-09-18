//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ControllerTime {
    public ControllerTime() {
    }
    //将时间进行转换成字符型
    public static String Get_Date_Time(Timestamp timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String current = formatter.format(timestamp.getTime());
        return current;
    }
    //将时间进行转换成字符型
    public static String Get_Duration_Time(int time) {
        String current;
        int f = time / 60;
        int s = time % 60;
        if (s==0) {
            current = f + "分钟";
        }else {
            current = f + "分钟" + s + "秒";
        }
        return current;
    }
    //时间转换成比例
    public static double Get_Analysis_Scale_Duration_Time(double time) {
        String scale = String.valueOf(time);
        String current = scale.substring(0, 5);
        double sc = Double.parseDouble(current);
        return sc;
    }
}
