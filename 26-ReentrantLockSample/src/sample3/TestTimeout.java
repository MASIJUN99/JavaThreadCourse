package sample3;

import util.SimpleLog;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestTimeout {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                // if (lock.tryLock()) {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    SimpleLog.complexLog("获得到锁");
                } else {
                    SimpleLog.complexLog("获取不到锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        SimpleLog.complexLog("主线程上锁");
        lock.lock();

        thread.start();
    }
}
