//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import main.java.net.friend.entity.Fabulous;
import main.java.tool.JdbcTool.DBBean;

public class FabulousDaoImpl implements FabulousDao {
    public FabulousDaoImpl() {
    }

    @Override
    public List<Fabulous> selectByPostId(int fabPostId) {
        List<Fabulous> list = new ArrayList<Fabulous>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `fabulous` where `f_post` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,fabPostId);
            rs = ps.executeQuery();
            while(rs.next()){
                Fabulous fabulous = new Fabulous();
                fabulous.setFabUserId(rs.getInt(1));
                fabulous.setFabPostId(rs.getInt(2));
                fabulous.setFabDate(rs.getDate(3));
                list.add(fabulous);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return list;
    }

    @Override
    public List<Fabulous> selectByUserId(int fabUserId) {
        List<Fabulous> list = new ArrayList<Fabulous>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `fabulous` where `f_user` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,fabUserId);
            rs = ps.executeQuery();
            while(rs.next()){
                Fabulous fabulous = new Fabulous();
                fabulous.setFabUserId(rs.getInt(1));
                fabulous.setFabPostId(rs.getInt(2));
                fabulous.setFabDate(rs.getDate(3));
                list.add(fabulous);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return list;
    }

    @Override
    public boolean selectByUserIdPostId(int userId, int postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBBean.getConnection();
            String sql = "select * from `fabulous` where `f_user` = ? and `f_post` = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,postId);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBBean.close(ps,conn);
        }
        return false;
    }

    public boolean delete(int fabUserId, int fabPostId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "delete from `fabulous` where f_user = ? and f_post = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fabUserId);
            ps.setInt(2, fabPostId);
            ps.executeUpdate();
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            DBBean.close(ps, conn);
        }

        return !this.isLike(fabUserId, fabPostId);
    }

    public boolean isUser(int fabUserId, int fabPostId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select * from post where u_id = ? and p_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fabUserId);
            ps.setInt(2, fabPostId);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return false;
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return true;
    }

    public boolean insert(Fabulous fabulous) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean var5 = false;

        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "insert into `fabulous`(`f_user`,`f_post`,`f_time`) values (?,?,now())";
            //插入语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fabulous.getFabUserId());
            ps.setInt(2, fabulous.getFabPostId());
            //执行命令
            ps.executeUpdate();
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return this.isLike(fabulous.getFabUserId(), fabulous.getFabPostId());
    }

    public boolean isLike(int userId, int postId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean var7;
        try {
            //创建数据库连接和命令对象
            conn = DBBean.getConnection();
            String sql = "select * from `fabulous` where f_user = ? and f_post = ?";
            //插询语句
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            //执行命令
            rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            }

            var7 = true;
        } catch (Exception var11) {
            var11.printStackTrace();
            return false;
        } finally {
            //关闭命令
            DBBean.close(ps, conn);
        }

        return var7;
    }
}
