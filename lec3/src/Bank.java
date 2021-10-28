import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bank extends Thread{
    static List<Bank> banks = new CopyOnWriteArrayList<Bank>();
    private final Object lock = new Object();
    private int t = 0;

    private int sum;
    private int count;
    private int id;

    public Bank(int sum, int count, int id) {
        super();

        this.sum = sum;
        this.count = count;
        this.id = id;

        banks.add(this);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            Bank bank = this;

            while (bank == this)
                bank = banks.get((int)(Math.random() * banks.size()));

            int money = (int)(Math.random() * 90 + 10);

            synchronized (lock) {
                t += 1;
                sum -= money;
            }

            System.out.println("Bank " + id + " send " + money + " in " + t + ", sum = " + sum);

            bank.send(money, t);

            try {
                sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {  }

        }
    }

    private void send(int money, int t) {
        synchronized (lock) {
            this.sum += money;
            this.t = Math.max(this.t, t) + 1;
        }

        System.out.println("Bank " + id + " get " + money + " in " + t + ", sum = " + sum);
    }
}
