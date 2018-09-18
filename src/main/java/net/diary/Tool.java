//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import main.java.net.diary.exception.ParameterException;

public class Tool {
    private static final ThreadLocal<SimpleDateFormat> SFS = new ThreadLocal();

    public Tool() {
    }

    public static void checkParams(int userId, String... params) throws ParameterException {
        if (userId == -1) {
            throw new ParameterException();
        } else {
            String[] var2 = params;
            int var3 = params.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String str = var2[var4];
                if (str == null || str.length() <= 0) {
                    throw new ParameterException();
                }
            }

        }
    }
    //将string类型的时间数据转化为指定类型的日期格式
    public static Date dateParser(String pattern, String date) throws ParameterException, ParseException {
        Date result;
        try {
            SimpleDateFormat sf = (SimpleDateFormat)SFS.get();
            if (sf == null) {
                sf = new SimpleDateFormat();
                SFS.set(sf);
            }

            sf.applyPattern(pattern);
            result = sf.parse(date);
        } catch (ParseException var7) {
            throw new ParameterException();
        } finally {
            SFS.remove();
        }

        return result;
    }

    public static int[] GetDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] dat = new int[]{calendar.get(1), calendar.get(2) + 1};
        return dat;
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
