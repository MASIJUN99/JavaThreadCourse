package method1;

/**
 * Method 1
 */
public class TestProducerAndConsumerMethodOne {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Thread(new Producer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
    }

}

class Producer implements Runnable {
    Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("produce " + i + " products");
            buffer.push(new Product(i));
        }
    }
}

class Consumer implements Runnable {
    Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("consume the " + buffer.pop().getId() + " products");
        }
    }
}

class Buffer {
    int size = 10;
    Product[] products = new Product[size];
    int cur = 0;

    public synchronized void push(Product product) {
        if (cur == size) {
            // the buffer is full and notice the consumer to consume
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        products[cur++] = product;
        this.notifyAll();
    }

    public synchronized Product pop() {
        if (cur == 0) {
            // the buffer is empty and notice the producer to produce
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = products[--cur];
        this.notifyAll();
        return product;
    }
}

class Product {
    private int id;

    Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}


