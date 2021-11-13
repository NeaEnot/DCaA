import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int n = 10;

        Process p = new Process(n);

        class Shared {
            public volatile int c = 0;
        }
        final Shared v = new Shared();

        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                p.lock();
                try {
                    v.c++;
                } finally {
                    p.unlock();
                }
                System.out.println("Поток: " + ThreadID.get() + ", разделяемая переменная = " + v.c);
            }
        };

        for (int i = 0; i < n; i++) {
            new Thread(r).start();
        }
    }
}