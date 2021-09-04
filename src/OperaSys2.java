
import Utils.WorkUtils;
import common.MemoryController;
import common.Process;
import common.Work;
import dispatch.RR;

import java.util.ArrayList;

/**
 * @author chenzhuohong
 */
public class OperaSys2 {

    /**
     * 时间片大小
     */
    private double timeSlice;

    /**
     * 所有的作业
     */
    private ArrayList<Work> allWork;

    /**
     * 所有与作业相关的进程
     */
    private ArrayList<Process> allProcess;

    /**
     * 系统的内存控制器
     */
    private MemoryController allMemory;

    public OperaSys2() {
    }

    /**
     * 初始化系统
     * @param memorySize 内存空间大小
     */
    public void initSystem(int memorySize){
        this.allProcess = new ArrayList<>();
        this.allMemory = new MemoryController(memorySize);
        this.allWork = WorkUtils.pushWorks();
    }

    /**
     * 运行作业
     * @param timeSlice 时间片轮转算法的时间片
     */
    public void operate(double timeSlice){
        for (Work work : this.allWork) {
            this.allProcess.add(new Process(work));
        }
        RR.roundRobin(this.allMemory, this.allProcess, timeSlice);
    }

    public static void main(String[] args) {
        OperaSys2 system = new OperaSys2();
        system.timeSlice = 1.7;
        system.initSystem(1024);
        system.operate(system.timeSlice);
    }

}
