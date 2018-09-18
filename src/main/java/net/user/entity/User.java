//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.user.entity;

public class User {
    private int userId;
    private String userName;
    private String pwd;
    private String telNumber;
    private String realName;
    private String idCard;
    private String state;
    private String dateTime;
    private String avator;
    public User() {
    }

    public User(int userId, String userName, String pwd, String telNumber, String realName, String idCard, String state) {
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.telNumber = telNumber;
        this.realName = realName;
        this.idCard = idCard;
        this.state = state;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String toString() {
        return "User{userId='" + this.userId + '\'' + ", userName='" + this.userName + '\'' + ", pwd='" + this.pwd + '\'' + ", telNumber='" + this.telNumber + '\'' + ", realName='" + this.realName + '\'' + ", idCard='" + this.idCard + '\'' + ", state='" + this.state + '\'' + ", dateTime=" + this.dateTime + '}';
    }
}
