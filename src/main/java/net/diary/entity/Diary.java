//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.diary.entity;

import java.io.IOException;
import java.util.Date;
import main.java.net.diary.Tool;

public class Diary {
    private int id;
    private int userId;
    private Date date;
    private String weather;
    private String mood;
    private String content;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public Diary(int userId, String weather, String mood, String content) {
        this.userId = userId;
        this.weather = weather;
        this.mood = mood;
        this.content = content;
    }

    public Diary(int id, int userId, Date date, String weather, String mood, String content) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.weather = weather;
        this.mood = mood;
        this.content = content;
    }

    public Diary(int id, Date date, String content, String mood, String weather) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.weather = weather;
        this.mood = mood;
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeather() {
        return this.weather;
    }

    public String getMood() {
        return this.mood;
    }

    public String getContent() {
        return this.content;
    }

    public String toListJson() throws IOException {
        return "{\"id\":" + this.id + ",\"date\":\"" + Tool.dateFormat("yyyy-MM-dd HH:mm", this.date) + "\",\"content\":\"" + this.content + "\",\"mood\":\"" + this.mood + "\",\"weather\":\"" + this.weather + "\"}";
    }

    public String toJson() throws IOException {
        return "{\"date\":\"" + Tool.dateFormat("yyyy-MM-dd HH:mm", this.date) + "\",\"weather\":\"" + this.weather + "\",\"mood\":\"" + this.mood + "\",\"content\":\"" + this.content + "\"}";
    }
}
