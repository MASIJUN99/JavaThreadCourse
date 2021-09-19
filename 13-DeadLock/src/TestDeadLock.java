public class TestDeadLock implements Runnable {

    static Resource1 resource1 = new Resource1();
    static Resource2 resource2 = new Resource2();

    // two choice
    int choice;
    String name;

    TestDeadLock(String name, int choice) {
        this.name = name;
        this.choice = choice;
    }

    @Override
    public void run() {
        try {
            getResource();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getResource() throws InterruptedException {
        if (choice == 1) {
            synchronized (resource1) {
                System.out.println(name + " get resource1!");
                Thread.sleep(1000);
                // move the second lock out of this lock to solve the dead lock.
                synchronized (resource2) {
                    System.out.println(name + " get resource2!");
                }
            }
        } else {
            synchronized (resource2) {
                System.out.println(name + " get resource2!");
                Thread.sleep(1000);
                // move the second lock out of this lock to solve the dead lock.
                synchronized (resource1) {
                    System.out.println(name + " get resource1!");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TestDeadLock("A", 1)).start();
        new Thread(new TestDeadLock("B", 2)).start();
    }
}

class Resource1 {

}

class Resource2 {

}

