public class Main {
    public static void main(String[] args) {
        //23 matite (100-23 = 77)
        //38 quaderni (35-38 = -3) <- Puppa

        Cartoleria.printInventory();
        Cliente c1 = new Cliente(5, 13);
        Cliente c2 = new Cliente(1, 4);
        Cliente c3 = new Cliente(10, 10);
        Cliente c4 = new Cliente(7, 11);
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
        } catch (InterruptedException e) {
            System.err.println("Puppa");
        }
        Cartoleria.printInventory();
    }
}
