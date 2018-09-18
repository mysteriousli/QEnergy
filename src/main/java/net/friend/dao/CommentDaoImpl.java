package main.java.net.friend.dao;

import main.java.net.friend.Tool;
import main.java.net.friend.entity.Comment;
import main.java.tool.JdbcTool.DBBean;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao{
    @Override
    public List<Comment> selectByPostId(int commPostId) {
        List<Comment> list = new ArrayList<Comment>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `comment` where com_post = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commPostId);
            rs = ps.executeQuery();
            list = setComment(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return list;
    }

    @Override
    public List<Comment> selectByUserId(int commUserId) {
        List<Comment> list = new ArrayList<Comment>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `comment` where com_user = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commUserId);
            rs = ps.executeQuery();
            list = setComment(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return list;
    }

    @Override
    public boolean delete(int commId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBBean.getConnection();
            String sql = "delete from comment where com_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return true;
    }

    @Override
    public boolean insert(Comment comment) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int oldCount = selectLast();
        boolean flag = false;
        try {
            conn = DBBean.getConnection();
            String sql = "insert into `comment`(`com_user`,`com_post`, `com_username`,`com_text`,`com_time`) values (?,?,?,?,now())";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,comment.getCommUserId());
            ps.setInt(2,comment.getCommPostId());
            ps.setString(3,comment.getCommUserName());
            ps.setString(4,comment.getCommText());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBBean.close(ps,conn);
        }
        return true;
    }

    @Override
    public int selectLatestRecord() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int com_id = -1;
        try {
            conn = DBBean.getConnection();
            String sql = "select top 1 * from `comment` Order By `com_time` Desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                com_id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBBean.close(ps,conn);
        }
        return com_id;
    }

    public List<Comment> setComment(ResultSet rs) throws SQLException, IOException {
        List<Comment> list = new ArrayList<Comment>();
        while(rs.next()){
            Comment comment = new Comment();
            comment.setCommId(rs.getInt(1));
            comment.setCommUserId(rs.getInt(2));
            comment.setCommUserName(rs.getString(3));
            comment.setCommPostId(rs.getInt(4));
            comment.setCommText(rs.getString(5));
            comment.setCommDate(Tool.dateFormat("yyyy-MM-dd HH:mm", rs.getTimestamp(6)));
            list.add(comment);
        }
        return list;
    }

    public int selectLast() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastId = -1;
        try{
            conn = DBBean.getConnection();
            String sql = "SELECT com_id FROM post ORDER BY com_id DESC LIMIT 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                lastId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return lastId;
    }
}
