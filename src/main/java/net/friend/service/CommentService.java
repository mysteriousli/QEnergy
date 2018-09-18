package main.java.net.friend.service;

import main.java.net.friend.dao.CommentDao;
import main.java.net.friend.dao.CommentDaoImpl;
import main.java.net.friend.entity.Comment;

import java.util.List;

public class CommentService {
    public boolean commentMsg(Comment comment){
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.insert(comment);
    }

    public int selectNewestFirst() {
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.selectLatestRecord();
    }


    public List<Comment> selectAllByPostId(int postId) {
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.selectByPostId(postId);
    }

    public List<Comment> selectAllByUserId(int userId) {
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.selectByUserId(userId);
    }

    public boolean deleteComment(int commId) {
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.delete(commId);
    }
}
