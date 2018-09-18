//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.net.friend.Tool;
import main.java.net.friend.entity.Post;
import main.java.tool.JdbcTool.DBBean;
import org.joda.time.DateTime;

public class PostDaoImpl implements PostDao {
    public PostDaoImpl() {
    }

    public List<Post> selectPostByUserId(int userId) {
        List<Post> list = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select * from post where u_id = ? order by p_date desc";
            //查询语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            //执行命令
            rs = ps.executeQuery();

            while(rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt(1));
                post.setUserName(rs.getString(2));
                post.setDate(format(rs.getTimestamp(4)));
                post.setText(rs.getString(5));
                post.setFabCount(rs.getInt(6));
                list.add(post);
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return list;
    }

    public int selectUserIdByPostId(int Id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = 0;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select u_id from post where p_id = ?";
            //查询语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            //执行命令
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt(1);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return userId;
    }

    public int insert(Post post, int userId) {
        int p_id = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "call insert_post(?,?)";
            //存储过程
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, post.getText());
            //执行命令
            rs = ps.executeQuery();
            rs.next();
            p_id = rs.getInt(1);
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return p_id;
    }

    public boolean deleteByPostId(int Id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "delete from post where p_id = ?";
            //删除语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            //执行命令
            ps.executeUpdate();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return true;
    }

    public List<Post> selectPostAll() {
        List<Post> list = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select * from `post` order by p_date desc";
            //查询语句
            ps = conn.prepareStatement(sql);
            //执行命令
            rs = ps.executeQuery();

            while(rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt(1));
                post.setUserName(rs.getString(2));
                post.setUserId(rs.getInt(3));
                post.setDate(format(rs.getTimestamp(4)));
                post.setText(rs.getString(5));
                post.setFabCount(rs.getInt(6));
                post.setPostTrans(rs.getInt(7));
                list.add(post);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return list;
    }

    public int selectLast() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lastId = -1;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "SELECT p_id FROM post ORDER BY p_id DESC LIMIT 1";
            //查询语句
            ps = conn.prepareStatement(sql);
            //执行命令
            rs = ps.executeQuery();
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return lastId;
    }

    @Override
    public boolean updateTransNum(int postId, int transNum) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DBBean.getConnection();
            String sql = "update user set `p_transNum`=? where `p_id` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,transNum);
            ps.setInt(2,postId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBBean.close(ps,conn);
        }
        return true;
    }
    //判断日期 昨天 今天
    private static String format(Date date) {

        DateTime now = new DateTime();
        DateTime today_start = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
        DateTime today_end = today_start.plusDays(1);
        DateTime yesterday_start = today_start.minusDays(1);

        if(date.after(today_start.toDate()) && date.before(today_end.toDate())) {
            return String.format("今天 %s", new DateTime(date).toString("HH:mm"));
        } else if(date.after(yesterday_start.toDate()) && date.before(today_start.toDate())) {
            return String.format("昨天 %s", new DateTime(date).toString("HH:mm"));
        }

        return new DateTime(date).toString("yyyy-MM-dd HH:mm");
    }
}
