package main.java.net.friend.dao;

import main.java.net.friend.entity.Comment;
import java.util.List;

public interface CommentDao {
    //列出帖子下方的所有评论
    public List<Comment> selectByPostId(int commPostId);
    //列出用户所评论过的所有帖子
    public List<Comment> selectByUserId(int commUserId);
    //删除评论
    public boolean delete(int commId);
    //插入一条评论
    public boolean insert(Comment comment);
    //显示最新的一条记录，用于返回帖子id
    public int selectLatestRecord();

}
