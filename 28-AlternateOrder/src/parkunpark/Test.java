package parkunpark;

import javax.naming.NamingException;
import java.util.concurrent.locks.LockSupport;

public class Test {

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {
        ParkUnpark parkUnpark = new ParkUnpark(5);

        t1 = new Thread(() -> {
            parkUnpark.print("a", t2);
        });

        t2 = new Thread(() -> {
            parkUnpark.print("b", t3);
        });

        t3 = new Thread(() -> {
            parkUnpark.print("c", t1);
        });

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);

    }
}

class ParkUnpark {

    private int loop;

    ParkUnpark(int loop) {
        this.loop = loop;
    }

    public void print(String text, Thread nxt) {
        for (int i = 0; i < loop; i++) {
            LockSupport.park();
            System.out.print(text);
            LockSupport.unpark(nxt);
        }
    }
}
