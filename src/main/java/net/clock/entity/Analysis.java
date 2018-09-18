//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.entity;

import java.util.List;

public class Analysis {
    private int totalCount;
    private int totalDuration;
    private int todayCount;
    private int todayDuration;
    private boolean isGet;
    private List<Lable> clockAnalysis;

    public Analysis(int totalCount, int totalDuration, int todayCount, int todayDuration, List<Lable> clockAnalysis) {
        this.totalCount = totalCount;
        this.totalDuration = totalDuration;
        this.todayCount = todayCount;
        this.todayDuration = todayDuration;
        this.clockAnalysis = clockAnalysis;
    }

    public boolean getIsGet() {
        return isGet;
    }

    public void setIsGet(boolean isget) {
        isGet = isget;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public int getTodayCount() {
        return this.todayCount;
    }

    public void setTodayCount(int todayCount) {
        this.todayCount = todayCount;
    }

    public int getTodayDuration() {
        return this.todayDuration;
    }

    public void setTodayDuration(int todayDuration) {
        this.todayDuration = todayDuration;
    }

    public List<Lable> getClockAnalysis() {
        return this.clockAnalysis;
    }

    public void setClockAnalysis(List<Lable> clockAnalysis) {
        this.clockAnalysis = clockAnalysis;
    }
}
