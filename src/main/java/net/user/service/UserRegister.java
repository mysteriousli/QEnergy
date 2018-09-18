//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.service;

import main.java.net.user.dao.UserDao;
import main.java.net.user.dao.UserDaoImpl;
import main.java.net.user.entity.User;

public class UserRegister {
    public UserRegister() {
    }
    //判断账号是否已存在
    public String registerVerdict(String telNumber) {
        String msg = null;
        UserDao userDao = new UserDaoImpl();
        //判断该手机好是否已注册
        if (userDao.selectUserByTelNumber(telNumber).getTelNumber() != null) {
            msg = "对不起！账号已存在！";
            return msg;
        } else {
            return msg;
        }
    }
    //添加用户账号
    public boolean add(User user) {
        UserDao userDao = new UserDaoImpl();
        //向数据库中插入用户数据
        return userDao.insert(user);
    }
}
