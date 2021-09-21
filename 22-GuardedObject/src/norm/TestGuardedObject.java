package norm;

import util.SimpleLog;

public class TestGuardedObject {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        // thread 1 wait for result of thread 2
        new Thread(() -> {
            SimpleLog.complexLog("thread1 is started");
            synchronized (guardedObject) {
                SimpleLog.complexLog("receive the result of thread2: {}", guardedObject.getResponse(2500L));
            }
        }, "t1").start();

        new Thread(() -> {
            SimpleLog.complexLog("thread2 is started");
            try {
                Thread.sleep(2000);  // simulate the delay of network
                guardedObject.setResponse("thread2 already done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

class GuardedObject {
    private Object response;

    public Object getResponse() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public Object getResponse(Long millis) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passed = 0;
            while (response == null) {
                long wait = millis - passed;
                if (wait <= 0) {
                    break;
                }
                try {
                    this.wait(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passed = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}