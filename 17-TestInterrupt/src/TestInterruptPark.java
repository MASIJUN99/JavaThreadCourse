import java.util.concurrent.locks.LockSupport;

public class TestInterruptPark {
    public static void main(String[] args) throws InterruptedException {
        testPark1();
        // testPark2();
    }

    static void testPark1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            SimpleLog.complexLog("park...");
            LockSupport.park();
            SimpleLog.complexLog("interrupt park...");
            SimpleLog.complexLog("the flag of interrupt: {}", Thread.currentThread().isInterrupted());

            LockSupport.park();  // park again
            SimpleLog.complexLog("interrupt park...");
            SimpleLog.complexLog("the flag of interrupt: {}", Thread.currentThread().isInterrupted());
        });
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }


    static void testPark2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            SimpleLog.complexLog("park...");
            LockSupport.park();
            SimpleLog.complexLog("interrupt park...");
            SimpleLog.complexLog("the flag of interrupt: {}", Thread.interrupted());

            // because the flag of
            LockSupport.park();  // park again, success
            SimpleLog.complexLog("interrupt park...");
            SimpleLog.complexLog("the flag of interrupt: {}", Thread.currentThread().isInterrupted());
        });
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }
}
