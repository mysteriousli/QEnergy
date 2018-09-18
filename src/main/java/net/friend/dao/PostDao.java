//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.dao;

import java.util.List;
import main.java.net.friend.entity.Post;

public interface PostDao {
    //查询个人用户帖子
    List<Post> selectPostByUserId(int var1);
    //查询用户id
    int selectUserIdByPostId(int var1);
    //插入帖子
    int insert(Post var1, int var2);
    //删除帖子
    boolean deleteByPostId(int var1);
    //查询全部帖子
    List<Post> selectPostAll();
    //修改转发数
    boolean updateTransNum(int postId, int transNum);
}
