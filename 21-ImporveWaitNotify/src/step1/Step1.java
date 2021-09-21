package step1;

import util.SimpleLog;

public class Step1 {
    static final Object room = new Object();
    static boolean hasCigar = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (room) {
                SimpleLog.complexLog("do you have cigar: [{}]", hasCigar);
                if (!hasCigar) {
                    SimpleLog.complexLog("do not have cigar, wait for a while!");
                    sleep(2);
                }
                SimpleLog.complexLog("do you have cigar: [{}]", hasCigar);
                if (hasCigar) {
                    SimpleLog.complexLog("lets start!");
                }
            }
        }, "A").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (room) {
                    SimpleLog.complexLog("start!");
                }
            }, "others").start();
        }

        sleep(1);
        new Thread(() -> {
            hasCigar = true;
            SimpleLog.complexLog("cigar express");
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
