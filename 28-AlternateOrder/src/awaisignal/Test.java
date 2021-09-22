package awaisignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a", a, b);
        }).start();

        new Thread(() -> {
            awaitSignal.print("b", b, c);
        }).start();

        new Thread(() -> {
            awaitSignal.print("c", c, a);
        }).start();


        // 必须是在lock状态下才能运行await
        Thread.sleep(1000);
        awaitSignal.lock();
        try {
            System.out.println("开始");
            a.signal();
        } finally {
            awaitSignal.unlock();
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private int loop;

    AwaitSignal(int loop) {
        this.loop = loop;
    }

    public void print(String text, Condition curr, Condition next) {
        for (int i = 0; i < loop; i++) {
            this.lock();
            try {
                curr.await();
                System.out.print(text);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.unlock();
            }
        }
    }
}
