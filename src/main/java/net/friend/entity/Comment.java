package main.java.net.friend.entity;

import java.util.Date;

public class Comment {
    private int commId;         //评论id
    private int commUserId;     //评论者id
    private int commPostId;     //被评论帖子id
    private String commText;    //评论内容
    private String commDate;      //评论时间
    private String commUserName;

    public Comment() {
    }

    public Comment(int commUserId, int commPostId, String commText, String commDate, int commId) {
        this.commUserId = commUserId;
        this.commPostId = commPostId;
        this.commText = commText;
        this.commDate = commDate;
        this.commId = commId;
    }

    public String getCommUserName() {
        return commUserName;
    }

    public void setCommUserName(String commUserName) {
        this.commUserName = commUserName;
    }

    public int getCommUserId() {
        return commUserId;
    }

    public void setCommUserId(int commUserId) {
        this.commUserId = commUserId;
    }

    public int getCommPostId() {
        return commPostId;
    }

    public void setCommPostId(int commPostId) {
        this.commPostId = commPostId;
    }


    public String getCommText() {
        return commText;
    }

    public void setCommText(String commText) {
        this.commText = commText;
    }

    public String getCommDate() {
        return commDate;
    }

    public void setCommDate(String commDate) {
        this.commDate = commDate;
    }

    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

}

