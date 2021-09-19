/**
 * if yield is successful, while a thread is not end, the other thread will start
 * like:
 *     this is thread A
 *     thread A stop
 *     this is thread B
 *     thread B stop
 *
 * if failed:
 *     this is thread B
 *     this is thread A
 *     thread B stop
 *     thread A stop
 */
public class TestYield {

    public static void main(String[] args) {
        new Thread(new TestYieldHelper("A")).start();
        new Thread(new TestYieldHelper("B")).start();
    }

}

class TestYieldHelper implements Runnable {

    private String name;

    TestYieldHelper(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("this is thread " + this.name);
        Thread.yield();
        System.out.println("thread " + this.name + " stop");
    }
}
