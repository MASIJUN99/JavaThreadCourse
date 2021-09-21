package enhance;

import com.sun.jndi.toolkit.url.GenericURLContext;
import util.SimpleLog;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class TestEnhanceGuardedObject {
    public static void main(String[] args) {
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        Consumer consumer3 = new Consumer();

        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

        new Thread(new Producer(consumer1, "Hello Zero")).start();
        new Thread(new Producer(consumer2, "Hello One")).start();
        new Thread(new Producer(consumer3, "Hello Two")).start();
    }
}

class Consumer implements Runnable {
    private int id;
    private EnhanceGuardedObject guardedObject;

    Consumer() {
        guardedObject = GuardedObjectContainer.createGuardedObject();
        id = guardedObject.getId();
    }

    @Override
    public void run() {
        SimpleLog.complexLog("消费者{}，准备消费", id);
        Object response = guardedObject.getResponse(5000);
        SimpleLog.complexLog("消费者{}收到: {}", id, response);
    }

    public int getId() {
        return id;
    }
}

class Producer implements Runnable {

    private Object msg;
    private int id;

    Producer(Consumer consumer, Object msg) {
        id = consumer.getId();
        this.msg = msg;
        SimpleLog.complexLog("创建生产者{}", id);
    }

    @Override
    public void run() {
        EnhanceGuardedObject enhanceGuardedObject = GuardedObjectContainer.getEnhanceGuardedObject(id);
        SimpleLog.complexLog("生产者{}准备发信: {}", id, msg);
        enhanceGuardedObject.setResponse(msg);
    }
}

class GuardedObjectContainer {
    private static final Map<Integer, EnhanceGuardedObject> container = new Hashtable<>();
    private static int id = 0;

    private static synchronized int getId() {
        return id++;
    }

    public static EnhanceGuardedObject getEnhanceGuardedObject(int id) {
        return container.remove(id);
    }

    public static EnhanceGuardedObject createGuardedObject() {
        EnhanceGuardedObject enhanceGuardedObject = new EnhanceGuardedObject(getId());
        container.put(enhanceGuardedObject.getId(), enhanceGuardedObject);
        return enhanceGuardedObject;
    }

    public Set<Integer> keys() {
        return container.keySet();
    }

}

class EnhanceGuardedObject {
    private final int id;
    private Object response;
    private final Object lock = new Object();

    EnhanceGuardedObject(int id) {
        this.id = id;
        this.response = null;
    }

    public int getId() {
        return id;
    }

    public Object getResponse() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.response;
    }

    public Object getResponse(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            long passed = 0;
            while (response == null) {
                long wait = timeout - passed;
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
        }
        return this.response;
    }

    public void setResponse(Object response) {
        synchronized (this) {
            this.response = response;
            this.notify();
        }
    }
}
