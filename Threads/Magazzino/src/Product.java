public class Product {
    private String name;
    private float price;
    public int quantity;

    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\n Price: $" + price + "\n Quantity: " + quantity + "\n";
    }
}
