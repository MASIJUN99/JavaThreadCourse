package waitnotify;

import util.SimpleLog;

public class Test {
    private static int curr = 0;
    private static int count = 0;


    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(1, 5);
        Thread t1 = new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }, "t1");

        Thread t2 = new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }, "t2");

        Thread t3 = new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WaitNotify {
    private int flag;
    private int loop;

    WaitNotify(int flag, int loop) {
        this.flag = flag;
        this.loop = loop;
    }

    public void print(String var, int currFlag, int nextFlag) {
        synchronized (this) {
            for (int i = 0; i < loop; i++) {
                while (flag != currFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(var);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
