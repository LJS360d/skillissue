public class Counter extends Thread {
    public int c;
    private long stopTime;

    public Counter() {
        this.c = 0;
        this.stopTime = System.currentTimeMillis() + 1;
    }

    @Override

    public void run() {
        while (System.currentTimeMillis() <= stopTime) {
            c++;
        }

    }
}
