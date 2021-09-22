package waitnotify;

import jdk.nashorn.internal.ir.Flags;
import util.SimpleLog;

public class Test {
    private static Object lock = new Object();
    private static boolean flag = false;  // 表示t2是否运行过

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (flag) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            SimpleLog.complexLog("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                SimpleLog.complexLog("2");
                lock.notify();
            }
        }, "t2");

        t1.start();
        t2.start();


    }
}
