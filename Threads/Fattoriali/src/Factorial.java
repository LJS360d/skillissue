package Threads.Fattoriali.src;

public class Factorial extends Thread {
    public static final int RANGE = 10;
    private int number;
    private long factored;
    private static long[][] matrix = new long[2][RANGE];
    private static int index = 0;

    public Factorial(int number) {
        this.number = number;
        this.factored = factorial(this.number);

    }

    @Override
    public void run() {
        synchronized (Factorial.class) {
            matrixInsert();
            System.out.println(matrix[0][index - 1] + " -> " + matrix[1][index - 1]);
            try {
                Thread.sleep(5);
            } catch (Exception e) {

            }
        }
    }

    private static long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;

        }
        return fact;

    }

    private synchronized void matrixInsert() {
        matrix[0][index] = this.number;
        matrix[1][index] = this.factored;
        index++;
    }
}
