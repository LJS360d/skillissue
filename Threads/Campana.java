package Threads;

public class Campana implements Runnable {
    private static final int DEFAULT_TIMES = 3;
    private String sound;
    private int times;

    public Campana(String sound) {
        this.sound = sound;
        this.times = DEFAULT_TIMES;
    }

    public Campana(String sound, int times) {
        this.sound = sound;
        this.times = times;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(sound);
            try {
                this.wait(420);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized static void main(String[] args) throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        Campana c = new Campana("Bing");
        Campana b = new Campana("Bong");
        Campana t = new Campana("Napoli");
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
    }
}
