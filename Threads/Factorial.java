package Threads;

public class Factorial extends Thread {
    public int number;
    public int factored;
    public static int[][] matrix = new int[2][10];
    public static int index = 0;

    public Factorial(int number) {
        this.number = number;
        this.factored = factorial(this.number);

    }

    @Override
    public void run() {
        synchronized (Factorial.class) {
            matrixInsert();
            System.out.println(matrix[0][index - 1] + " -> " + matrix[1][index - 1]);
        }

    }

    private static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;

        }
        return fact;

    }

    private synchronized void matrixInsert() {
        if (index != 10) {
            matrix[0][index] = this.number;
            matrix[1][index] = this.factored;
            index++;
        } else
            System.err.println("Kitammurt");
    }
}
