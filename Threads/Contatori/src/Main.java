public class Main {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();
        c1.setPriority(Thread.MAX_PRIORITY);
        c2.setPriority(5);
        c3.setPriority(Thread.MIN_PRIORITY);
        c1.start();
        c2.start();
        c3.start();
        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println(c1.c + " " + c2.c + " " + c3.c);
    }
}
