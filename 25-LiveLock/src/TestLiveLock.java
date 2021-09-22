import util.SimpleLog;

public class TestLiveLock {
    private static int count = 10;
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count >= 0) {
                try {
                    Thread.sleep(1000);
                    count -= 1;
                    SimpleLog.complexLog("count--: {}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (count < 20) {
                try {
                    Thread.sleep(1000);
                    count += 1;
                    SimpleLog.complexLog("count++: {}", count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
