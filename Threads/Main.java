package Threads;

import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        randomizer(7);
    }

    public static void randomizer(int threads) {
        int size = 10;

        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while (list.size() > (10 - threads)) {
            int index = rand.nextInt(list.size());
            Factorial t = new Factorial(list.remove(index));
            t.start();

        }

    }
}
