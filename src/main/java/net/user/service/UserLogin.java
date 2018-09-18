//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.service;

import main.java.net.user.dao.UserDao;
import main.java.net.user.dao.UserDaoImpl;

public class UserLogin {
    public UserLogin() {
    }
    //判断账号是否存在
    public String telNumberIsExist(String telNumber) {
        String msg = null;
        UserDao dao = new UserDaoImpl();
        //判断该手机好是否已注册
        if (dao.selectUserByTelNumber(telNumber).getTelNumber() == null) {
            msg = "对不起！账号不存在！";
            return msg;
        } else {

            return msg;
        }
    }
    //判断用户输入的密码是否正确
    public String pwdIsRight(String enterPwd, String telNumber) {
        UserDao dao = new UserDaoImpl();
        String userPwd = dao.selectUserByTelNumber(telNumber).getPwd();
        //判断用户输入的密码是否正确
        return enterPwd.equals(userPwd) ? "" : "对不起！密码错误！";
    }
}
