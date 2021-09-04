package common;

/**
 * @author chenzhuohong
 */
public class Process {

    /**
     * 进程id，与作业id相同
     */
    private String pid;

    /**
     * 进程要完成的作业
     */
    private Work work;

    /**
     * 使用的内存块头
     * -1表示已完成
     */
    private int memoryHead;

    /**
     * 进程已运行时间
     */
    private double usedTime;

    /**
     * 进程状态
     */
    private char status;

    public Process(){}

    public Process(Work work) {
        this.pid = work.getWid();
        this.work = work;
        this.memoryHead = 0;
        this.usedTime = 0;
        this.status = 'W';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public int getMemoryHead() {
        return memoryHead;
    }

    public void setMemoryHead(int head) {
        this.memoryHead = head;
    }

    public double getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(double usedTime) {
        this.usedTime = usedTime;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Process{" +
                "进程id'" + this.getPid() + '\'' +
                ", " + this.getWork() +
                ", 所在内存首地址" + this.getMemoryHead() +
                ", 已运行时间" + this.getUsedTime() +
                ", 进程状态'" + this.getStatus() + '\'' +
                '}';
    }
}
