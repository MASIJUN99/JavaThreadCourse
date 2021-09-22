package sample4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestConditionVars {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        // 创建一个新的条件变量
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        lock.lock();
        // 进入条件变量等待
        condition1.await();

        // 通知condition1
        condition1.signal();
        condition1.signalAll();
    }
}
