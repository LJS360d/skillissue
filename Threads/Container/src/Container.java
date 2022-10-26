import java.util.Queue;

public class Container extends ThreadGroup {
    private Queue<Thread> storage;

    public Container(String name) {
        super(name);
    }

    public void insert(Thread... toInsert) {
        for (Thread thread : toInsert) {
            if (!storage.contains(thread)) {
                storage.add(thread);
            } else {
                System.err.println(thread.getName() + " is already in storage");
            }
        }
    }

    public Thread getByName(String name) {
        for (Thread thread : storage) {
            if (thread.getName() == name) {
                return thread;
            }
        }
        return null;
    }

    public void interruptByName(String name) {
        for (Thread thread : storage) {
            if (thread.getName() == name) {
                thread.interrupt();
            }
        }
    }

    public void joinByName(String name) throws InterruptedException {
        for (Thread thread : storage) {
            if (thread.getName() == name) {
                thread.join();
            }
        }
    }

    public void removeByName(String name) throws InterruptedException {
        for (Thread thread : storage) {
            if (thread.getName() == name) {
                storage.remove(thread);
                thread.interrupt();
                thread.join();
            }
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

    public void joinAll() throws InterruptedException {
        for (Thread thread : storage) {
            thread.join();
        }
    }

}