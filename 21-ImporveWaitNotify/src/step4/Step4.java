package step4;

import sun.management.HotSpotDiagnostic;
import util.SimpleLog;

public class Step4 {
    static final Object room = new Object();
    static boolean hasCigar = false;
    static boolean hasDeliver = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                SimpleLog.complexLog("do you have cigar: [{}]", hasCigar);
                while (!hasCigar) {
                    SimpleLog.complexLog("do not have cigar, wait for a while!");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SimpleLog.complexLog("do you have cigar: [{}]", hasCigar);
                if (hasCigar) {
                    SimpleLog.complexLog("lets start!");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (room) {
                SimpleLog.complexLog("do you have deliver: [{}]", hasDeliver);
                while (!hasDeliver) {
                    SimpleLog.complexLog("do not have deliver, wait for a while!");
                    try {
                        room.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SimpleLog.complexLog("do you have deliver: [{}]", hasDeliver);
                if (hasDeliver) {
                    SimpleLog.complexLog("lets start!");
                }
            }
        }, "B").start();

        sleep(1);
        new Thread(() -> {
            synchronized (room) {
                hasDeliver = true;
                room.notifyAll();
                SimpleLog.complexLog("cigar express");
            }
        }, "express man").start();
    }

    public static void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
