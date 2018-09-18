//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
    private static final ThreadLocal<SimpleDateFormat> SFS = new ThreadLocal();

    public Tool() {
    }
    //将Date类型的时间数据转化为指定类型的日期格式
    public static String dateFormat(String pattern, Date date) throws IOException {
        String result;
        try {
            SimpleDateFormat sf = (SimpleDateFormat)SFS.get();
            if (sf == null) {
                sf = new SimpleDateFormat();
                SFS.set(sf);
            }

            sf.applyPattern(pattern);
            result = sf.format(date);
        } catch (Exception var7) {
            throw new IOException();
        } finally {
            SFS.remove();
        }
        return result;
    }
}
