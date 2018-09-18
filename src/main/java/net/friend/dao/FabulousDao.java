//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.dao;

import java.util.List;
import main.java.net.friend.entity.Fabulous;

public interface FabulousDao {
    //显示点赞用户
    List<Fabulous> selectByPostId(int fabPostId);
    //查询用户赞过哪些帖子
    List<Fabulous> selectByUserId(int fabUserId);
    //查询当前用户是否点过赞
    boolean selectByUserIdPostId(int userId,int postId);
    //删除点赞
    boolean delete(int var1, int var2);
    //增加点赞
    boolean insert(Fabulous var1);
    //判断时候已点赞
    boolean isLike(int var1, int var2);
}
