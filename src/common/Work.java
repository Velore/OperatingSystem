package common;

import Utils.RandomUtils;

/**
 * @author chenzhuohong
 */
public class Work {

    /**
     * 作业id
     */
    private String wid;
    /**
     * 运行需要的内存
     */
    private int needMemory;

    /**
     * 运行需要的时间
     */
    private double needTime;

    /**
     * 作业状态：true已完成，false未完成
     */
    private boolean status;

    public Work(int needMemory, double needTime) {
        this.wid = RandomUtils.randomName();
        this.needMemory = needMemory;
        this.needTime = needTime;
        this.status = false;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public int getNeedMemory() {
        return needMemory;
    }

    public void setNeedMemory(int needMemory) {
        this.needMemory = needMemory;
    }

    public double getNeedTime() {
        return needTime;
    }

    public void setNeedTime(double needTime) {
        this.needTime = needTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "作业id'" + this.getWid() + '\'' +
                ", 运行内存" + this.getNeedMemory() +
                ", 运行时间" + this.getNeedTime() +
                ", 作业状态" + this.getStatus() +
                '}';
    }
}
