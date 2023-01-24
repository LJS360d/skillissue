public class Customer extends Thread {
    private static final int PRODUCT_AMOUNT = 3;
    private static volatile int activeCustomers = 0;
    private String[] products = new String[PRODUCT_AMOUNT];
    private float debit = 0;

    public Customer(String product) {
        super("Customer-" + ++activeCustomers);
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
        while (true) {

            for (int i = 0; i < PRODUCT_AMOUNT; i++) {
                for (String string : products) {
                    synchronized (Magazzino.class) {
                        while (Magazzino.shifting) {
                            try {
                                Magazzino.class.wait();
                            } catch (Exception e) {
                                return;
                            }
                        }
                        if (string != null) {
                            if (Magazzino.popProduct(string) >= 0) {
                                Magazzino.shifting = true;
                                Magazzino.class.notifyAll();
                                System.out.println(this.getName() + ":Ho Comprato 1 di->" + string + "," +
                                        +Magazzino.getProductByName(string).quantity + " Rimasta");
                                this.debit += Magazzino.getProductByName(string).price;
                            } else {
                                Magazzino.shifting = true;
                                Magazzino.class.notifyAll();
                                Magazzino.checkOutOfStock();
                            }
                        }
                    }
                }
            }
            System.out.println(this.getName() + ":Ho speso $" + debit);
        }
    }
}
