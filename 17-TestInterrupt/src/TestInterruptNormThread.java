public class TestInterruptNormThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            SimpleLog.complexLog("im going into a dead loop!");
            while (!Thread.currentThread().isInterrupted()) {

            }
            // while (true) {}  // to use this will not end the thread
        });
        thread.start();

        Thread.sleep(1000);

        SimpleLog.complexLog("i will interrupt the thread!");
        SimpleLog.complexLog("the interrupt flag: {}", thread.isInterrupted());
        thread.interrupt();
        SimpleLog.complexLog("the interrupt flag: {}", thread.isInterrupted());
    }
}
