package sample1;

import util.SimpleLog;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrant {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            SimpleLog.complexLog("main is starting...");
            m1();
        } finally {
            lock.unlock();
        }

    }

    public static void m1() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            SimpleLog.complexLog("m1 is starting...");
            m2();
        } finally {
            lock.unlock();
        }
    }

    public static void m2() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            SimpleLog.complexLog("m2 is starting...");
        } finally {
            lock.unlock();
        }
    }
}
