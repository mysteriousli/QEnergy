//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.entity;

import java.util.Date;
import main.java.net.bill.Tool;

public class Bill {
    private int id;
    private int userId;
    private Date date;
    private String label;
    private String iconCode;
    private String type;
    private double money;
    private static final String MONEY_PATTERN = "0.00";

    public Bill(int id, int userId, Date date, String label, String iconCode, String type, double money) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.label = label;
        this.iconCode = iconCode;
        this.type = type;
        this.money = money;
    }

    public Bill(int userId, Date date, String label, String iconCode, String type, double money) {
        this.userId = userId;
        this.date = date;
        this.label = label;
        this.iconCode = iconCode;
        this.type = type;
        this.money = money;
    }

    public String getIconCode() {
        return this.iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabel() {
        return this.label;
    }

    public String getType() {
        return this.type;
    }

    public double getMoney() {
        return this.money;
    }

    String toListJson() {
        return "{\"id\":" + this.id + ",\"label\":\"" + this.label + "\",\"iconCode\":\"" + this.iconCode + "\",\"type\":\"" + this.type + "\",\"money\":\"" + Tool.numberFormat("0.00", this.money) + "\"}";
    }
}
