//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.bill.entity;

import java.util.ArrayList;
import java.util.Iterator;

public class DailyList {
    private ArrayList<Bill> bills = new ArrayList();

    public DailyList() {
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

    public String toJsonArray() {
        StringBuilder buff = new StringBuilder();
        boolean flag = true;
        buff.append('[');

        Bill bill;
        for(Iterator var3 = this.bills.iterator(); var3.hasNext(); buff.append(bill.toListJson())) {
            bill = (Bill)var3.next();
            if (flag) {
                flag = false;
            } else {
                buff.append(',');
            }
        }

        buff.append(']');
        return buff.toString();
    }
}
