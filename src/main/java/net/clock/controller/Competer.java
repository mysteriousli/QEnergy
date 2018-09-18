//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.java.net.clock.entity.Lable;

public class Competer {
    public Competer() {
    }
    //将任务的标签进行必将排序
    public static List<Lable> Compere_List(List<Lable> list) {
        Collections.sort(list, new Comparator<Lable>() {
            public int compare(Lable o1, Lable o2) {
                return Integer.valueOf(o1.getLabel().charAt(0)) - Integer.valueOf(o2.getLabel().charAt(0));
            }
        });
        return list;
    }
}
