//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.entity;

public class Post {
    private int id;
    private String userName;
    private String date;
    private String text;
    private int fabCount;
    private int postTrans;
    private int userId;
    public Post() {
    }

    public Post(int id, String date, String text, int fabCount) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.fabCount = fabCount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFabCount() {
        return this.fabCount;
    }

    public void setFabCount(int fabCount) {
        this.fabCount = fabCount;
    }

    public int getPostTrans() {
        return postTrans;
    }

    public void setPostTrans(int postTrans) {
        this.postTrans = postTrans;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
