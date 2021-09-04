package Utils;

import common.Work;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author chenzhuohong
 */
public class WorkUtils {

    /**
     * 向作业队列中添加一个随机的作业
     * @param workList 作业队列
     */
    public static void addRandomWork(ArrayList<Work> workList){
        workList.add(new Work(RandomUtils.randomInt(200, 300), RandomUtils.randomInt(15)));
    }

    /**
     * 向作业队列中随机添加 n 个随机的作业
     * @param n 要添加的作业数量
     * @param workList 作业队列
     */
    public static void addRandomWork(int n, ArrayList<Work> workList){
        for(int i = 0;i<n;i++){
            workList.add(new Work(RandomUtils.randomInt(1024), RandomUtils.randomInt(15)));
        }
    }

    /**
     * 设置新的作业
     * @return 新的作业
     */
    public static Work setNewWork(){
        double needTime;
        int needMemory;
        Scanner nw = new Scanner(System.in);
        System.out.println("请输入作业需要的内存");
        needMemory = nw.nextInt();
        System.out.println("请输入作业要运行的时间");
        needTime = nw.nextInt();
        return new Work(needMemory, needTime);
    }

    /**
     * 输出作业的相关信息
     * @param workList 作业队列
     */
    public static void printWork(ArrayList<Work> workList){
        if(workList.isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for(Work work : workList){
            System.out.println(work.toString());
        }
    }

    /**
     * 修改某个作业
     * @param workList 作业队列
     */
    public static void updateWork(ArrayList<Work> workList){
        WorkUtils.printWork(workList);
        Scanner uw = new Scanner(System.in);
        String input;
        System.out.println("请输入你想修改的作业的id");
        input = uw.next();
        for (Work work : workList) {
            if (work.getWid().equals(input)) {
                System.out.println("请输入作业需要运行的内存");
                work.setNeedMemory(uw.nextInt());
                System.out.println("请输入作业需要运行的时间");
                work.setNeedTime(uw.nextDouble());
                return;
            }
        }
        System.out.println("没有找到该作业，请重新输入作业id");
    }

    /**
     * 向作业队列中加入新的作业
     * @return 作业队列
     */
    public static ArrayList<Work> pushWorks(){
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<Work> workList = new ArrayList<>();
        boolean flag = true;
        while(flag){
            System.out.println(
                    "命令1:设置一个新作业\t" +
                            "2:修改一个作业\t" +
                            "3:查看所有的作业\n" +
                            "4:随机设置一个新作业\t" +
                            "5:随机设置10个新作业\t" +
                            "e:退出设置，开始运行");
            System.out.println("请输入命令");
            input = sc.next();
            switch (input){
                case "1":
                    workList.add(WorkUtils.setNewWork());
                    break;
                case "2":
                    WorkUtils.updateWork(workList);
                    break;
                case "3":
                    WorkUtils.printWork(workList);
                    break;
                case "4":
                    WorkUtils.addRandomWork(workList);
                    break;
                case "5":
                    WorkUtils.addRandomWork(10, workList);
                    break;
                case "e":
                    flag = false;
                    break;
                default:
                    System.out.println("请输入正确的命令");
            }
        }
        return workList;
    }

}
