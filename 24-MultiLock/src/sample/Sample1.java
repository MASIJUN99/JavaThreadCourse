package sample;

import util.SimpleLog;

/**
 * in this sample, if two people use same room, it will not suitable.
 */
public class Sample1 {
    public static void main(String[] args) {
        Room room = new Room();
        new Thread(() -> {
            try {
                room.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                room.study();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Room {
    public void sleep() throws InterruptedException {
        synchronized (this) {
            SimpleLog.complexLog("i need sleep 2 hrs");
            Thread.sleep(2000);
        }
    }

    public void study() throws InterruptedException {
        synchronized (this) {
            SimpleLog.complexLog("i need study 1 hrs");
            Thread.sleep(1000);
        }
    }
}
