package Threads.Fattoriali.src;

import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        randomizer(10);
    }

    public static void randomizer(int threads) {

        if (threads > Factorial.RANGE) {
            throw new IndexOutOfBoundsException(
                    "\n No duplicates idiot, Number of Threads must be under or equal to RANGE");
        }

        ArrayList<Integer> list = new ArrayList<Integer>(Factorial.RANGE);
        Random rand = new Random();

        for (int i = 1; i <= Factorial.RANGE; i++) {
            list.add(i);
        }
        while (list.size() > (Factorial.RANGE - threads)) {
            int index = rand.nextInt(list.size());
            new Factorial(list.remove(index)).start();
        }

    }
}
