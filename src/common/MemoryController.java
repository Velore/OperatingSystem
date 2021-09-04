package common;

import Utils.ProcessUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author chenzhuohong
 */
public class MemoryController {

    /**
     * 内存空间
     */
    private final LinkedList<Memory> memory;

    public MemoryController(int size){
        this.memory = new LinkedList<>();
        memory.add(new Memory(0, size));
    }

    /**
     * 判断是否有足够的空闲内存被分配
     * @param mc 内存控制器
     * @param size 要分配的内存大小
     * @return
     */
    public static boolean enoughMemory(MemoryController mc, int size){
        int workLocate = -1;
        for (Memory value : mc.memory) {
            if (value.isFree() && value.getSize() >= size) {
                workLocate = value.getHead();
                break;
            }
        }
        //            System.out.println("enoughMemory:无可用内存空间!");
        return workLocate != -1;
    }

    /**
     * 最佳适应算法
     * @param size 指定需要分配的大小
     */
    private int bestFit(int size){
        int workLocate = -1;
        int sizeFit = 1000;
        for (Memory value : this.memory) {
            if (value.isFree() && value.getSize() >= size) {
                if (value.getSize() - size < sizeFit) {
                    workLocate = value.getHead();
                    sizeFit = value.getSize() - size;
                }
            }
        }
        for(int i = 0; i<this.memory.size() ;i++){
            if(this.memory.get(i).getHead()==workLocate){
                Memory m = this.memory.get(i);
                workLocate = m.getHead();
                m.setHead(m.getHead()+size);
                m.setSize(m.getSize()-size);
                Memory newWork = new Memory(workLocate, size);
                newWork.setFree(false);
                this.memory.add(i, newWork);
                return workLocate;
            }
        }
        return workLocate;
    }

    /**
     * 回收内存空间
     * @param wid 要回收内存的进程id
     * @param pcbLink 内存中的全部进程
     */
    public void recycle(String wid, ArrayList<Process> pcbLink){
        int i;
        Memory tmp = null;
        for(i = 0;i<memory.size();i++){
            if(memory.get(i).getHead() == ProcessUtils.findProcess(wid, pcbLink).getMemoryHead()){
                tmp = memory.get(i);
                break;
            }
        }
        if(tmp==null){
            System.out.println("没有找到任务所在内存");
            return;
        }
        if(tmp.isFree()){
            System.out.println("该内存未被使用，无需回收");
            return;
        }else{
            tmp.setFree(true);
            Process p = ProcessUtils.findProcess(wid, pcbLink);
            if(p!=null){
                p.setMemoryHead(-1);
            }
        }
        //合并后面的一个空闲内存块
        if(i<this.memory.size()-1 && memory.get(i+1).isFree()){
            tmp.setSize(tmp.getSize()+memory.get(i+1).getSize());
            memory.remove(i+1);
        }
        //合并前面的一个空闲内存块
        if(i>=1 && memory.get(i-1).isFree()){
            tmp.setSize(tmp.getSize()+memory.get(i-1).getSize());
            tmp.setHead(tmp.getHead()-memory.get(i-1).getSize());
            memory.remove(i-1);
        }
        System.out.println("recycle");
        printMemory();
    }

    /**
     * 输出内存空间
     */
    public void printMemory(){
        System.out.println("--------------------------------");
        System.out.println("分区编号\t分区始址\t分区大小\t空闲状态");
        for (int i = 0; i < memory.size(); i++){
            Memory tmp = memory.get(i);
            System.out.printf("%4d\t\t%4d\t\t%4d\t\t%b\n"
                    , (i+1)
                    , tmp.getHead()
                    , tmp.getSize()
                    , tmp.isFree());
        }
        System.out.println("--------------------------------");
    }

    /**
     * 给进程分配内存
     * @param wid 要分配内存的进程id
     * @param size 要分配内存的大小
     * @param pcbLink 内存中的所有进程
     */
    public void allocation(String wid, int size, ArrayList<Process> pcbLink){
        Process p = ProcessUtils.findProcess(wid, pcbLink);
        if(p!=null){
            p.setMemoryHead(bestFit(size));
        }
        printMemory();
    }

    public static void main(String[] args) {
    }

}
