package Utils;

import common.MemoryController;
import common.Process;
import common.Work;

import java.util.ArrayList;

/**
 * @author chenzhuohong
 */
public class ProcessUtils {

    /**
     * 从所有的进程中寻找id匹配的进程
     * @param pid 进程id
     * @param allProcess 所有的进程（包括就绪队列的等待队列）
     * @return id匹配的进程
     */
    public static Process findProcess(String pid, ArrayList<Process> allProcess){
        for(Process p : allProcess){
            if(p.getPid().equals(pid)){
                return p;
            }
        }
        return null;
    }

    /**
     * 把进程加入就绪队列
     * @param ap1 被取出进程的队列
     * @param ap2 就绪队列
     */
    public static void updatePcbLink(MemoryController mc, ArrayList<Process> ap1, ArrayList<Process> ap2){
        for(Process p :ap1){
            if(ap2.size()<5){
                if(!ap2.contains(p) && p.getStatus() == 'W') {
                    if(MemoryController.enoughMemory(mc, p.getWork().getNeedMemory())){
                        ap2.add(p);
                        mc.allocation(p.getPid(), p.getWork().getNeedMemory(), ap2);
                    }
                }
            }else{
                return;
            }
        }

    }

    /**
     * 进程是否已全部完成
     * @param ap 要判断的所有进程
     * @return 已全部完成返回true，否则返回false
     */
    public static boolean allDone(ArrayList<Process> ap){
        for(Process p :ap){
            if(p.getStatus() != 'F') {
                return false;
            }
        }
        return true;
    }

    public static void printName(ArrayList<Process> ap){
        if(ap.isEmpty()){
            System.out.println("队列为空");
        }else{
            for(Process p : ap){
                System.out.print(p.getPid()+"\t");
            }
        }
    }

    /**
     * 输出进程的相关信息
     * @param p 要输出的所有进程
     */
    public static void printProcessData(ArrayList<Process> p){
        if(p.isEmpty()){
            System.out.println("队列为空");
        }else{
            System.out.println("进程id 需要时间 运行时间 需要内存 内存头 进程状态");
            for (Process process : p) {
                System.out.printf("%s\t%4.1f\t%4.1f\t%4d\t%4d\t%3c\n"
                        , process.getPid()
                        , process.getWork().getNeedTime()
                        , process.getUsedTime()
                        , process.getWork().getNeedMemory()
                        , process.getMemoryHead()
                        , process.getStatus());
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Process> allProcess = new ArrayList<>();
        for(int i = 0 ; i<5 ; i++) {
            Process process = new Process(new Work(RandomUtils.randomInt(10, 20), RandomUtils.randomInt(5, 10)));
            process.setMemoryHead(100);
            allProcess.add(process);
            System.out.println(process);
        }
        printProcessData(allProcess);
    }
}
