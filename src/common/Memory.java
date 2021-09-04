package common;

/**
 * @author chenzhuohong
 */
public class Memory {

    private int head;
    private int size;
    private boolean isFree;

    public Memory(){
    }

    public Memory(int head, int size){
        this.head = head;
        this.size = size;
        this.isFree = true;
    }

    public int getHead(){
        return this.head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return this.isFree;
    }

    public void setFree(boolean free) {
        this.isFree = free;
    }

    @Override
    public String toString(){
        return " {内存始址" + this.getHead() +
                " 使用情况" + this.isFree() +
                " 内存大小" + this.getSize() + "}";
    }
}

