public class Main {
    public static void main(String[] args)
    {
        Bank bank0 = new Bank(1000, 10, 0);
        Bank bank1 = new Bank(1000, 10, 1);
        Bank bank2 = new Bank(1000, 10, 2);
        Bank bank3 = new Bank(1000, 10, 3);
        Bank bank4 = new Bank(1000, 10, 4);

        bank0.start();
        bank1.start();
        bank2.start();
        bank3.start();
        bank4.start();

        try {
            bank0.join();
            bank1.join();
            bank2.join();
            bank3.join();
            bank4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
