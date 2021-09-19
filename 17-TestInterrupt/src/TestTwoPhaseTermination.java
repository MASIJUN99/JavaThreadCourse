import java.util.Currency;

public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(5000);
        tpt.stop();
    }
}

class TwoPhaseTermination {
    private Thread monitor;

    // start the monitor thread
    public void start() {
        monitor = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    SimpleLog.complexLog("monitor is running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // the interrupt while sleeping will reset the flag
                    monitor.interrupt();  // so we need to make sure it was interrupted!
                }
            }
            SimpleLog.complexLog("monitor is stopped!");
        });
        monitor.start();
    }

    // stop the monitor thread
    public void stop() {
        monitor.interrupt();
    }
}

