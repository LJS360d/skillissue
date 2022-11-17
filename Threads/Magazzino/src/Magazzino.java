import java.util.LinkedList;

public class Magazzino {
    private static LinkedList<Product> storage = new LinkedList<Product>();

    public static void add(Product... toAdd) {
        for (Product product : toAdd) {
            Magazzino.storage.add(product);
        }
    }

    public static Product getProductByName(String name) {
        for (Product product : storage) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public static boolean popProduct(String name) {
        for (Product product : storage) {
            synchronized (product) {
                if (product.getName().equals(name) && product.quantity > 0) {
                    product.quantity--;
                    return true;
                }
            }
        }
        return false;
    }

    public static void listProducts() {
        for (Product product : storage) {
            if (product.quantity > 0) {
                System.out.print(product.toString());
            }
        }
    }

    public static void checkOutOfStock() {
        for (Product product : storage) {
            if (product.quantity == 0) {
                System.out.println("Abbiamo finito ->" + product.getName());
            }
        }
    }
}
