package sample2;

import util.SimpleLog;

import java.util.concurrent.locks.ReentrantLock;

public class TestInterrupt {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                SimpleLog.complexLog("尝试获得锁");
                lock.lockInterruptibly();
                SimpleLog.complexLog("或得到锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
                SimpleLog.complexLog("被打断");
                return;
            } finally {
                lock.unlock();
            }
        });

        lock.lock();

        thread.start();
        Thread.sleep(3000);

        SimpleLog.complexLog("打断！");
        thread.interrupt();
    }
}
