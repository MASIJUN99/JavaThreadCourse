public class TestDaemon {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonThread());
        Thread normThead = new Thread(new NormThread());

        daemonThread.setDaemon(true);
        daemonThread.start();

        normThead.start();
    }
}

class DaemonThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("this is daemon thread");
        }
    }
}

class NormThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("im running!");
        }
        System.out.println("im stopped!");
    }
}
