import sun.awt.windows.ThemeReader;

public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("i will occupy the current thread");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);

        for (int i = 0; i < 1000; i++) {
            System.out.println("im running...");
            if (i == 200) {
                thread.start();
                thread.join();
                System.out.println("occupied!");
            }
        }
    }
}
