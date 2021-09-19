public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread is running...");
            }
        });
        System.out.println(thread.getState());
        thread.start();
        while (thread.getState() != Thread.State.TERMINATED) {
            Thread.sleep(200);
            System.out.println(thread.getState());
        }

        // thread.start();  // this is wrong.
    }
}
