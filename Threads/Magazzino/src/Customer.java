public class Customer extends Thread {
    private String[] products = new String[3];

    public Customer(String product) {
        super();
        this.products[0] = product;
    }

    public boolean addToWishlist(String product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        for (String string : products) {
            if (string != null) {
                if (Magazzino.popProduct(string)) {
                    System.out.println(this.getName() + ":Ho comprato ->" + string);
                } else {
                    Magazzino.checkOutOfStock();
                }
            }
        }
    }
}
