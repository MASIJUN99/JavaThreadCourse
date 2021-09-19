public class TestPriority {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new TestThread());
        Thread thread2 = new Thread(new TestThread());
        Thread thread3 = new Thread(new TestThread());
        Thread thread4 = new Thread(new TestThread());
        Thread thread5 = new Thread(new TestThread());
        Thread thread6 = new Thread(new TestThread());
        Thread thread7 = new Thread(new TestThread());

        // set priority
        thread1.setPriority(1);
        thread2.setPriority(2);
        thread3.setPriority(3);
        thread4.setPriority(4);
        thread5.setPriority(5);
        thread6.setPriority(6);
        thread7.setPriority(7);

        // start
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
    }
}

class TestThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has " + Thread.currentThread().getPriority() + " priority");
    }
}