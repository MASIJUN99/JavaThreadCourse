import util.SimpleLog;

public class TestTwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(4000);
        SimpleLog.complexLog("停止监控线程");
        twoPhaseTermination.stop();
    }
}

class TwoPhaseTermination {
    private Thread monitor;

    private boolean started = false;

    // 加上volatile关键字让这个可见性扩大
    private volatile boolean stop = false;

    public void start() {
        synchronized (this) {
            if (started) {
                return;
            } else {
                started = true;
            }
        }
        monitor = new Thread(() -> {
            while (true) {
                Thread thread = Thread.currentThread();
                if (stop) {
                    SimpleLog.complexLog("结束");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    SimpleLog.complexLog("执行监控线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "monitor");
        monitor.start();
    }

    public void stop() {
        synchronized (this) {
            if (started && !stop) {
                stop = true;
                monitor.interrupt();
                started = false;
            }
        }
    }
}
