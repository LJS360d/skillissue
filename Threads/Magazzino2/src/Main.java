public class Main {
    public static void main(String[] args) throws InterruptedException {
        Magazzino.build(
                new Product("Tuta", 30, 1),
                new Product("Puppaggio", 1000, 1),
                new Product("Nutella", 5, 1));
        Magazzino.listProducts();
        Customer c1 = new Customer("Nutella");
        Restock r1 = new Restock("Nutella");

        Customer c2 = new Customer("Puppaggio");
        Restock r2 = new Restock("Puppaggio");

        Customer c3 = new Customer("Tuta");
        // c3.addToWishlist("Puppaggio");
        // c3.addToWishlist("Nutella");
        Restock r3 = new Restock("Tuta");
        c3.start();
        // r1.start();
        // r2.start();
        r3.start();
    }

}
