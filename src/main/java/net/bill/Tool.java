//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import main.java.net.bill.exception.ParameterException;

public class Tool {
    private static final ThreadLocal<SimpleDateFormat> SFS = new ThreadLocal();
    private static final DecimalFormat DF = new DecimalFormat();
    public static final String INCOME_FLAG = "收入";
    public static final String PAY_FLAG = "支出";
    private static final String MONEY_PATTERN = "0.00";

    public Tool() {
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
    //将string类型的时间数据转化为指定类型的日期格式
    public static Date dateParser1(String pattern, String date) throws ParameterException, ParseException {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(11);
        int mi = cal.get(12);
        int s = cal.get(13);

        Date result;
        try {
            SimpleDateFormat sf = (SimpleDateFormat)SFS.get();
            if (sf == null) {
                sf = new SimpleDateFormat();
                SFS.set(sf);
            }

            sf.applyPattern(pattern);
            result = sf.parse(date + " " + h + ":" + mi + ":" + s);
        } catch (ParseException var11) {
            throw new ParameterException();
        } finally {
            SFS.remove();
        }

        return result;
    }
    //将int类型的数据转化为指定类型
    public static String numberFormat(String pattern, int num) {
        DF.applyPattern(pattern);
        return DF.format((long)num);
    }
    //将double类型的数据转化为指定类型
    public static String numberFormat(String pattern, double num) {
        DF.applyPattern(pattern);
        return DF.format(num);
    }
    //检查账单数据的正确性
    public static void checkParams(int userId, String... params) throws ParameterException {
        if (String.valueOf(userId) == null) {
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
    //获取账单的年与日
    public static int[] GetDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] dat = new int[]{calendar.get(1), calendar.get(2) + 1};
        return dat;
    }
    //检察账单类型
    public static int checkBillType(String type) throws ParameterException {
        if ("收入".equals(type)) {
            return 1;
        } else if ("支出".equals(type)) {
            return -1;
        } else {
            throw new ParameterException();
        }
    }
}
