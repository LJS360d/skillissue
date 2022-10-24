
/**
 * Available at
 * {@link https://github.com/LJS360d/skillissue/tree/master/Threads}
 * 
 * 
 * @author Luca Lencioni
 */
public class Campana extends Thread {
    private final static int DEFAULT_TIMES = 2;
    private int times;
    private String sound;

    public Campana(String sound) {
        this.sound = sound;
        this.times = DEFAULT_TIMES;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            print();

        }
    }

    private synchronized void print() {
        this.notifyAll();
        System.out.println(this.sound);
        try {
            this.wait(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
