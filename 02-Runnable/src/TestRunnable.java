/**
 *
 */
public class TestRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("runnable thread...");
        }
    }

    public static void main(String[] args) {

//        TestRunnable testRunnable = new TestRunnable();
//        Thread thread = new Thread(testRunnable);
//        thread.start();

        new Thread(new TestRunnable()).start();


        for (int i = 0; i < 20; i++) {
            System.out.println("main thread...");
        }
    }
}

