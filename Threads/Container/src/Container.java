import java.util.Queue;

public class Container extends ThreadGroup {
    private Queue<Thread> storage;

    public Container(String name) {
        super(name);
    }

    public void insert(Thread... toInsert) {
        for (Thread thread : toInsert) {
            storage.add(thread);
        }
    }

    public void startAll() {
        for (Thread thread : storage) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }
    }

    public void interruptAll() {
        for (Thread thread : storage) {
            thread.interrupt();
        }
    }

    public void joinAll() {
        for (Thread thread : storage) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Kitammurt");
            }
        }
    }

}