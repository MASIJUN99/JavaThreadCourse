import util.SimpleLog;

import java.util.LinkedList;

public class TestBlockedQueue {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                Message message = new Message(finalI, "Hello " + finalI);
                messageQueue.put(message);
            }, "生产者" + i).start();
        }


        new Thread(() -> {
            while (true) {
                try {
                    // simulate log of network
                    Thread.sleep(1000);
                    Message message = messageQueue.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者").start();
    }
}


/**
 * 线程间通信的消息队列
 */
class MessageQueue {

    private LinkedList<Message> deque = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message get() {
        synchronized (deque) {
            while (deque.isEmpty()) {
                try {
                    SimpleLog.complexLog("队列空，消费者等待...");
                    deque.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = deque.removeFirst();
            SimpleLog.complexLog("消费者消费消息: id = {}, msg = {}", message.getId(), message.getMsg());
            deque.notifyAll();
            return message;
        }
    }

    public void put(Message message) {
        synchronized (deque) {
            while (deque.size() >= capacity) {
                try {
                    SimpleLog.complexLog("队列满，生产者等待...");
                    deque.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.add(message);
            SimpleLog.complexLog("生产者添加消息: id = {}, msg = {}", message.getId(), message.getMsg());
            deque.notifyAll();
        }
    }
}

/**
 * 消息实体类
 */
final class Message {
    private int id;
    private Object msg;

    Message(int id, Object msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public Object getMsg() {
        return msg;
    }
}