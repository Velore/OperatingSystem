package dispatch;

import common.MemoryController;
import common.Process;
import Utils.ProcessUtils;
import common.Work;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @author chenzhuohong
 */
public class RR {

    public static double currentTime;
    public static int processNum;
    public static double timeSlice;
    public static MemoryController mc;

    /**
     * 运行进程
     */
    public static void roundRobin(MemoryController mc, ArrayList<Process> allProcess, double timeSlice) {
        currentTime = 0;
        DecimalFormat df = new DecimalFormat(".00");

        //初始化就绪队列
        ArrayList<Process> pcbLink = new ArrayList<>();

//        System.out.println("以下是所有要运行的进程");
//        ProcessUtils.printListWithTime(allProcess);
//        System.out.println("时间片："+timeSlice+"\n");

        //进程运行直至全部运行完成
        while(!ProcessUtils.allDone(allProcess)) {
//
            if(currentTime >= 1.0){
                System.out.println("-------------------------");
                System.out.println("当前时间："+currentTime);
                System.out.println("所有进程：");
                ProcessUtils.printProcessData(allProcess);
                System.out.println("-------------------------");
            }
//
            ProcessUtils.updatePcbLink(mc, allProcess, pcbLink);

            if(!pcbLink.isEmpty()){
                for(int i = 0 ; i<pcbLink.size() ;){
                    if(pcbLink.get(i).getUsedTime()+timeSlice >= pcbLink.get(i).getWork().getNeedTime()){
                        currentTime += pcbLink.get(i).getWork().getNeedTime()-pcbLink.get(i).getUsedTime();
                        currentTime = Double.parseDouble(df.format(currentTime));
                        pcbLink.get(i).setStatus('R');
                        pcbLink.get(i).setUsedTime(pcbLink.get(i).getWork().getNeedTime());
//
//                        System.out.println("当前时间:"+ currentTime+
//                                "  进程"+ pcbLink.get(i).getPid()+
//                                "已运行完成");
//                        System.out.println(pcbLink.get(i).timeData());
//
                        pcbLink.get(i).setStatus('F');
                        mc.recycle(pcbLink.get(i).getPid(), pcbLink);
                        pcbLink.remove(i);
//
//                        System.out.println("就绪队列：");
//                        ProcessUtils.printListWithTime(pcbLink);
//                        System.out.println("\n");
//
                        break;
                    }else{
                        pcbLink.get(i).setStatus('R');
                        pcbLink.get(i).setUsedTime(
                                Double.parseDouble(
                                        df.format(pcbLink.get(i).getUsedTime()+timeSlice)
                                ));
                        currentTime = Double.parseDouble(df.format(currentTime += timeSlice));
                        pcbLink.get(i).setStatus('W');
                        ProcessUtils.updatePcbLink(mc, allProcess, pcbLink);
                    }
                    i = (i+1)%pcbLink.size();
                }
            }else{
                currentTime = Double.parseDouble(df.format(currentTime += 0.1));
            }
            if(currentTime >= 200){
                break;
            }
        }
        ProcessUtils.printProcessData(allProcess);
    }

    public static void main(String[] args){
        processNum = 5;
        timeSlice = 1.2;
        mc = new MemoryController(1000);
        //创建进程池
        ArrayList<Process> allProcess = new ArrayList<>();
        //创建所有要运行的进程
        for(int i = 0 ; i < processNum ; i++) {
            allProcess.add(new Process( new Work(10, 10)));
        }
        roundRobin(mc, allProcess ,timeSlice);
    }

}

