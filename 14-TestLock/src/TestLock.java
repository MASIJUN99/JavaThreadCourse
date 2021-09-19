import java.util.concurrent.locks.ReentrantLock;

class TestLock implements Runnable {

    private int ticketNums = 10;
    private boolean flag = true;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (flag) {
            try {
                lock.lock();
                buy();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void buy() {
        if (ticketNums > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " get ticket " + ticketNums--);
        } else {
            flag = false;
        }
    }

    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        new Thread(testLock, "A").start();
        new Thread(testLock, "B").start();
        new Thread(testLock, "C").start();
    }
}
