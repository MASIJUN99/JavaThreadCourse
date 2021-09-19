public class UnsafeBuyTicket {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();

        new Thread(tickets, "A").start();
        new Thread(tickets, "B").start();
        new Thread(tickets, "C").start();
    }
}

class Tickets implements Runnable {

    private int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    // after add this we can buy the tickets in locked
    private synchronized void buy() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ticketNums > 0) {
            System.out.println(Thread.currentThread().getName() + " get No." + ticketNums--);
        } else {
            flag = false;
        }
    }
}
