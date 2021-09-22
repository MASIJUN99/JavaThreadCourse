package parkunpark;

import util.SimpleLog;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            SimpleLog.complexLog("1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            SimpleLog.complexLog("2");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
