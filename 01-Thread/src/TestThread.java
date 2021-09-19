/**
 * 1. 创建类，并继承Thread类
 * 2. 重写run方法
 * 3. 创建类，调用start()
 */
public class TestThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("multi thread...");
        }
    }

    /**
     * to run this, you will see the result alternate with multi thread and main thread.
     */
    public static void main(String[] args) {

        TestThread testThread = new TestThread();
        testThread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("single thread");
        }

    }
}
