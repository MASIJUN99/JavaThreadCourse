public class PracticeFour {

    public synchronized void a() throws InterruptedException {
        Thread.sleep(1000);
        SimpleLog.complexLog("do method a...");
    }

    public synchronized void b() {
        SimpleLog.complexLog("do method b...");
    }


    public static void main(String[] args) {
        PracticeFour p = new PracticeFour();
        PracticeFour p2 = new PracticeFour();
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
