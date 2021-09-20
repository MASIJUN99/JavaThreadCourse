public class PracticeSeven {

    public synchronized static void a() throws InterruptedException {
        Thread.sleep(1000);
        SimpleLog.complexLog("do method a...");
    }

    public synchronized void b() {
        SimpleLog.complexLog("do method b...");
    }


    public static void main(String[] args) {
        PracticeSeven p = new PracticeSeven();
        PracticeSeven p2 = new PracticeSeven();
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
            p2.b();
        }).start();


    }

}
