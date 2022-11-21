import java.util.LinkedList;

public class Magazzino {
    private static LinkedList<Product> storage = new LinkedList<Product>();
    public static Boolean shifting = false;

    public static void build(Product... toAdd) {
        if (storage.size() < 5) {
            for (Product product : toAdd) {
                Magazzino.storage.add(product);
            }
        } else {
            System.out.println("We Full");
        }
    }

    public static boolean addToProduct(String name) {
        Magazzino.shifting = true;
        if (Magazzino.getProductByName(name) != null) {
            Magazzino.getProductByName(name).quantity++;
            return true;
        }
        return false;
    }

    public static Product getProductByName(String name) {
        for (Product product : storage) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public static int popProduct(String name) {
        for (Product product : storage) {
            if (product.getName().equals(name) && product.quantity > 0) {
                return --product.quantity;
            }

        }
        return -1;
    }

    public static void listProducts() {
        System.out.println("----------------");
        for (Product product : storage) {
            if (product.quantity > 0) {
                System.out.print(product.toString());
            }
        }
        System.out.println("----------------");
    }

    public static void checkOutOfStock() {
        for (Product product : storage) {
            if (product.quantity == 0) {
                System.out.println("Abbiamo " + product.quantity + " di " + product.getName());
            }
        }
    }
}
