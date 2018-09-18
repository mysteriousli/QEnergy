//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.entity;

public class DailyAnalysis {
    private double pay = 0.0D;
    private double income = 0.0D;

    public DailyAnalysis() {
    }

    public void record(double money) {
        if (money > 0.0D) {
            this.income += money;
        } else {
            this.pay += -1.0D * money;
        }

    }

    public double getPay() {
        return this.pay;
    }

    public double getIncome() {
        return this.income;
    }
}
