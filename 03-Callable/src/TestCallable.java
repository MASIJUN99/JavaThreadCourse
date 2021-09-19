import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.*;

public class TestCallable implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println("callable thread...");
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable();

        ExecutorService ser = Executors.newFixedThreadPool(1);

        Future<Boolean> submit = ser.submit(testCallable);

        Boolean result = submit.get();

        ser.shutdownNow();


        for (int i = 0; i < 20; i++) {
            System.out.println("main thread...");
        }
    }
}
