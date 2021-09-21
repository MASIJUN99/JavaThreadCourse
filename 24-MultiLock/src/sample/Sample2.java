package sample;

import util.SimpleLog;

/**
 * in this sample, if two people use same room, it will not suitable.
 */
public class Sample2 {
    public static void main(String[] args) {
        BigRoom room = new BigRoom();
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

class BigRoom {

    private Object studyRoom = new Object();
    private Object sleepRoom = new Object();

    public void sleep() throws InterruptedException {
        synchronized (sleepRoom) {
            SimpleLog.complexLog("i need sleep 2 hrs");
            Thread.sleep(2000);
        }
    }

    public void study() throws InterruptedException {
        synchronized (studyRoom) {
            SimpleLog.complexLog("i need study 1 hrs");
            Thread.sleep(1000);
        }
    }
}
