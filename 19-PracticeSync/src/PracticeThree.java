public class PracticeThree {

    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        SimpleLog.complexLog("do method a...");
    }

    public synchronized void b() {
        SimpleLog.complexLog("do method b...");
    }

    public void c() {
        SimpleLog.complexLog("do method c...");
    }

    public static void main(String[] args) {
        PracticeThree p = new PracticeThree();
        new Thread(() -> {
            SimpleLog.complexLog("begin");
            try {
                p.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            SimpleLog.complexLog("begin");
            p.b();
        }).start();
        new Thread(() -> {
            SimpleLog.complexLog("begin");
            p.c();
        }).start();

    }

}
