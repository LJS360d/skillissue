public class Restock extends Thread {
    private static final int PRODUCT_AMOUNT = 3;
    private String[] products = new String[PRODUCT_AMOUNT];
    private static volatile int activeRestocks = 0;

    public Restock(String product) {
        super("Restock-" + ++activeRestocks);
        this.products[0] = product;
    }

    public boolean addToRestockables(String product) {
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
        for (int i = 0; i < PRODUCT_AMOUNT; i++) {
            for (String string : products) {
                synchronized (Magazzino.class) {
                    while (!Magazzino.shifting) {
                        try {
                            Magazzino.class.wait();
                        } catch (Exception e) {
                            return;
                        }
                    }
                    if (string != null) {
                        if (Magazzino.addToProduct(string)) {
                            Magazzino.shifting = false;
                            Magazzino.class.notifyAll();
                            System.out.println(this.getName() + ":Ho Aggiunto 1 di->" + string + "," +
                                    +Magazzino.getProductByName(string).quantity + " Rimasta");
                        } else {
                            Magazzino.shifting = false;
                            Magazzino.class.notifyAll();
                            Magazzino.checkOutOfStock();
                        }
                    }
                }
            }
        }
        System.out.println(this.getName() + ":Io torno a casa");
    }
}
