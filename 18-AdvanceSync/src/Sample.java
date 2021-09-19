public class Sample {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        haveSync();
    }

    static void withoutSync() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter += 1;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter -= 1;
            }
        });

        SimpleLog.complexLog("start...");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        SimpleLog.complexLog("end and counter: {}", counter);
    }

    static void haveSync() throws InterruptedException {
        Object obj = new Object();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (obj) {
                    counter += 1;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (obj) {
                    counter -= 1;
                }
            }
        });

        SimpleLog.complexLog("start...");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        SimpleLog.complexLog("end and counter: {}", counter);
    }
}
