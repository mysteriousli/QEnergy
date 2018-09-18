//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.dao;

import main.java.net.user.entity.User;

public interface UserDao {
    //通过手机号码进行用户的查找
    User selectUserByTelNumber(String var1);
    //向数据库中插入用户
    boolean insert(User var1);
    //查询用户的id
    int selectLast();
    //根据用户id查询用户
    User selectUserByUserId(int userId);
}
