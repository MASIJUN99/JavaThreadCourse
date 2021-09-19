package method2;

public class TestProducerAndConsumerMethodTwo {

    public static void main(String[] args) {
        Product product = new Product();
        new Thread(new Producer(product)).start();
        new Thread(new Consumer(product)).start();


    }

}

class Producer implements Runnable {

    private Product product;

    Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            product.produce("product" + i);
        }
    }

}

class Consumer implements Runnable {

    Product product;

    Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            product.consume();
        }
    }

}

class Product {
    private String name;
    private boolean flag = false;

    public void produce(String name) {
        try {
            if (flag) {
                this.wait();
            }
            System.out.println("produce the " + name);
            flag = true;
            this.name = name;
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void consume() {
        try {
            if (!flag) {
                this.wait();
            }
            System.out.println("consume the " + this.name);
            flag = false;
            this.name = null;
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

