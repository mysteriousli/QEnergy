//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.tool.JdbcTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DBBean {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static Properties pr = new Properties();

    private DBBean() {
    }
    //创建数据库的求链接对象
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
    //关闭数据库的链接对象及操作命令
    public static void close(Statement stmt, Connection conn) {
        try {
            stmt.close();
            conn.close();
        } catch (Exception var3) {
            ;
        }

    }
    //加载数据库链接的基本数据
    static {
        try {
            pr.load(DBBean.class.getClassLoader().getResourceAsStream("main/java/tool/JdbcTool/db.properties"));
            driver = pr.getProperty("driver");
            url = pr.getProperty("url");
            user = pr.getProperty("username");
            password = pr.getProperty("password");
            Class.forName(driver);
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
