package main.java.net.friend.entity;

import java.util.List;
import java.util.Map;

public class PostM {
    private int id;
    private String message;
    private List<Map> discussMap;

    public PostM(int id,String message,List<Map> discussMap){
       this.id = id;
       this.message = message;
       this.discussMap = discussMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Map> getDiscussMap() {
        return discussMap;
    }

    public void setDiscussMap(List<Map> discussMap) {
        this.discussMap = discussMap;
    }
}
