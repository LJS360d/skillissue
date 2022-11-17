public class Main {
    public static void main(String[] args) {
        Magazzino.add(
                new Product("Nutella", 3, 10),
                new Product("Carta", 60, 5),
                new Product("Puppaggio", 1000, 1),
                new Product("Tuta", 2, 4));
        Magazzino.listProducts();
        System.out.println("----------------");
        Customer c1 = new Customer("Nutella");
        Customer c2 = new Customer("Tuta");
        c1.addToWishlist("Puppaggio");
        c2.addToWishlist("Puppaggio");

        c1.start();
        c2.start();
    }

}
