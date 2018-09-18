//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.service;

import java.util.*;

import main.java.net.friend.dao.*;
import main.java.net.friend.entity.Comment;
import main.java.net.friend.entity.Fabulous;
import main.java.net.friend.entity.Post;
import main.java.net.friend.entity.PostM;
import main.java.net.user.dao.UserDao;
import main.java.net.user.dao.UserDaoImpl;
import main.java.net.user.entity.User;
import org.joda.time.DateTime;

public class PostService {

    public PostService() {
    }

    public int PostMsg(Post post, int userId) {
        PostDao postDao = new PostDaoImpl();
        return postDao.insert(post, userId);
    }

    public int selectNewestFirst(){
        PostDao postDao = new PostDaoImpl();
        return ((PostDaoImpl) postDao).selectLast();
    }

    public List<Map> selectPostByUserId(int userId){
        //根据userId获得帖子帖子内容再根据帖子内容中的帖子id获取所有的评论此帖子的内容
        PostDao postDao = new PostDaoImpl();
        UserDao userDao = new UserDaoImpl();
        FabulousDao fabulousDao = new FabulousDaoImpl();
        CommentDao commentDao = new CommentDaoImpl();
        List<Map> discussMap = new ArrayList<>();//总map
        List<Post> postList = new ArrayList<Post>();
        postList = postDao.selectPostByUserId(userId);
        for(int i = 0;i<postList.size();i++){//遍历postList中的内容，查找相关评论
            String fabMsg = "";
            //查找相关评论
            Post post = new Post();
            post = postList.get(i);//获取当前post   帖子id 发帖人id 发帖时间帖子全部内容 点赞数
            User user = new User();
            user = userDao.selectUserByUserId(post.getUserId());
            int postId = post.getId();//帖子id
            List list = new ArrayList();
            list.add(post);//在list中加入post对象
            //用户点赞
            List<Fabulous> fabulousList = new ArrayList<Fabulous>();//点赞用户
            fabulousList = fabulousDao.selectByPostId(postId);
            if(fabulousList.size()<=0) {
                fabMsg = "没有用户点赞";
            }else  {
                for (int j = 0; j < fabulousList.size(); j++) {
                    if (j==fabulousList.size()-1) {
                        fabMsg = fabMsg + userDao.selectUserByUserId(fabulousList.get(j).getFabUserId()).getUserName();
                    }else {
                        fabMsg = fabMsg + userDao.selectUserByUserId(fabulousList.get(j).getFabUserId()).getUserName() + "、";
                    }
                }
                if(fabulousList.size()<=5) {
                    fabMsg = fabMsg + "觉得很赞！";
                }else{
                    fabMsg = fabMsg + "等"+fabulousList.size()+"人觉得很赞！";
                }
            }
            //转发数
            int transNum = postList.get(i).getPostTrans();
            //当前用户是否点赞
            boolean isFab = false;
            isFab = fabulousDao.selectByUserIdPostId(userId,postId);
            //评论
            List<Comment> commentList = commentDao.selectByPostId(postId);
            int commNum = commentList.size();
            Map map = new HashMap();
            //整合到discussList中
            map.put("id",postId);//帖子id
            map.put("userName",user.getUserName());//发帖人用户名
            map.put("text",post.getText());//帖子内容
            map.put("data",post.getDate());//发帖时间 今天 昨天 其他
            map.put("fabMsg",fabMsg);//xxx等x人点赞
            map.put("commNum",commNum);//评论数
            map.put("transNum",transNum);//转发数
            map.put("isFab",isFab);//是否点赞
            map.put("commList",commentList);//评论列表
            discussMap.add(map);
        }
        return discussMap;

    }

    public List<Map> selectPostAll(int userId){
        //根据userId获得帖子帖子内容再根据帖子内容中的帖子id获取所有的评论此帖子的内容
        PostDao postDao = new PostDaoImpl();
        UserDao userDao = new UserDaoImpl();
        FabulousDao fabulousDao = new FabulousDaoImpl();
        CommentDao commentDao = new CommentDaoImpl();
        List<Map> discussMap = new ArrayList<>();//总map
        List<Post> postList = new ArrayList<Post>();
        postList = postDao.selectPostAll();
        for(int i = 0;i<postList.size();i++){//遍历postList中的内容，查找相关评论
            String fabMsg = "";
            //查找相关评论
            Post post = new Post();
            post = postList.get(i);
            User user = new User();
            user = userDao.selectUserByUserId(post.getUserId());
            int postId = post.getId();//帖子;//帖子id
            List list = new ArrayList();
            list.add(post);//在list中加入post对象
            //用户点赞
            List<Fabulous> fabulousList = new ArrayList<Fabulous>();//点赞用户
            fabulousList = fabulousDao.selectByPostId(postId);
            if(fabulousList.size()<=0) {
                fabMsg = "没有用户点赞";
            }else  {
                for (int j = 0; j < fabulousList.size(); j++) {
                    if (j==fabulousList.size()-1) {
                        fabMsg = fabMsg + userDao.selectUserByUserId(fabulousList.get(j).getFabUserId()).getUserName();
                    }else {
                        fabMsg = fabMsg + userDao.selectUserByUserId(fabulousList.get(j).getFabUserId()).getUserName() + "、";
                    }
                }
                if(fabulousList.size()<=5) {
                    fabMsg = fabMsg + "觉得很赞！";
                }else{
                    fabMsg = fabMsg + "等"+fabulousList.size()+"人觉得很赞！";
                }
            }
            //转发数
            int transNum = postList.get(i).getPostTrans();
            //当前用户是否点赞
            boolean isFab = false;
            isFab = fabulousDao.selectByUserIdPostId(userId,postId);
            //评论
            List<Comment> commentList = commentDao.selectByPostId(postId);
            int commNum = commentList.size();
            Map map = new HashMap();
            //整合到discussList中
            map.put("id",postId);//帖子id
            map.put("userName",user.getUserName());//发帖人用户名
            map.put("text",post.getText());//帖子内容
            map.put("date",post.getDate());//发帖时间 今天 昨天 其他
            map.put("fabMsg",fabMsg);//xxx等x人点赞
            map.put("commNum",commNum);//评论数
            map.put("transNum",transNum);//转发数
            map.put("isFab",isFab);//是否点赞
            map.put("commList",commentList);//评论列表
            discussMap.add(map);
        }
        return discussMap;
    }

    public int selectFabCount(int postId){
        FabulousDao fabulousDao = new FabulousDaoImpl();
        List<Fabulous> fabulous  = new ArrayList<>();
        fabulous = fabulousDao.selectByPostId(postId);
        return fabulous.size();
    }

    public boolean deletePost(int postId) {
        PostDao postDao = new PostDaoImpl();
        return postDao.deleteByPostId(postId);
    }

    public String getUserName(int postId) {
        PostDao postDao = new PostDaoImpl();
        int userId = postDao.selectUserIdByPostId(postId);
        UserDao userDao = new UserDaoImpl();
        return userDao.selectUserByUserId(userId).getUserName();
    }

    public void transNumAdd(int postId) {
        PostDao postDao = new PostDaoImpl();
        int num = postDao.selectUserIdByPostId(postId);
        num++;
        postDao.updateTransNum(postId,num);
    }
}
