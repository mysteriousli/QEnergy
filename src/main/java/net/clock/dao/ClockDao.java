//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.clock.dao;

import java.util.List;
import main.java.net.clock.entity.Analysis;
import main.java.net.clock.entity.Clock;

public interface ClockDao {
    //创建任务
    int Create_Clock(Clock var1, int var2) throws Exception;
    //查询任务列表
    List<Clock> Show_TodoList(int var1) throws Exception;
    //中断任务
    Boolean Interrupt_Clock(int var1) throws Exception;
    //完成任务
    Boolean Finish_Clock(int c_id) throws Exception;
    //获得任务分析结果
    Analysis Show_Analysis(int var1) throws Exception;
}
