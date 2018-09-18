//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.controller;

import java.util.Comparator;
import main.java.net.clock.entity.Lable;

final class Competer1 implements Comparator<Lable> {
    //将任务的标签进行必将排序
    public int compare(Lable o1, Lable o2) {
        return Integer.valueOf(o1.getLabel().charAt(0)) - Integer.valueOf(o2.getLabel().charAt(0));
    }
}
