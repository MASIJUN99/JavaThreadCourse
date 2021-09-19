import sun.java2d.pipe.SpanShapeRenderer;

public class TestInterruptSleepThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestThread());
        thread.start();

        Thread.sleep(1000);  // delay sometime

        SimpleLog.complexLog("try to interrupt!");
        thread.interrupt();
        SimpleLog.complexLog("the interrupt flag: {}", thread.isInterrupted());
    }
}

class TestThread implements Runnable {
    @Override
    public void run() {
        SimpleLog.complexLog("start sleeping...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleLog.complexLog("im awake!");
    }
}
