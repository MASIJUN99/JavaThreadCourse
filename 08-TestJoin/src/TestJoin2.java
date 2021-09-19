public class TestJoin2  {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new t1());
        Thread t2 = new Thread(new t2());
        SimpleLog.simpleLog("%date [%t] %logger start begin");
        t1.start();
        t2.start();
        long tic = System.currentTimeMillis();
        SimpleLog.simpleLog("%date [%t] %logger join begin");
        t1.join();
        t2.join();
        long toc = System.currentTimeMillis();
        SimpleLog.simpleLog("%date [%t] %logger cost: {}", toc - tic);

    }
}

class t1 implements Runnable {
    @Override
    public void run() {
        SimpleLog.simpleLog("%date [%t] %logger t1 is started!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleLog.simpleLog("%date [%t] %logger t1 is done!");
    }
}

class t2 implements Runnable {
    @Override
    public void run() {
        SimpleLog.simpleLog("%date [%t] %logger t2 is started!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleLog.simpleLog("%date [%t] %logger t2 is done!");
    }
}
