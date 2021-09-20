public class PracticeOne {

    public synchronized void a() {
        SimpleLog.complexLog("do method a...");
    }

    public synchronized void b() {
        SimpleLog.complexLog("do method b...");
    }

    public static void main(String[] args) {
        PracticeOne practiceOne = new PracticeOne();
        new Thread(() -> {
            SimpleLog.complexLog("begin");
            practiceOne.a();
        }).start();
        new Thread(() -> {
            SimpleLog.complexLog("begin");
            practiceOne.b();
        }).start();

    }

}
