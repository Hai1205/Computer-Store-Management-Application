package element;

public class Component extends Product {
    public Component() {
    }

    public Component(String productID, String productName, int quantity, int price, String unit) {
        super(productID, productName, quantity, price, unit);
    }

    public Component(Component other) {
        super((Product) other);
    }

    @Override
    public void input() {
        super.input();
    }

    @Override
    public void display() {
        System.out.println(
                "-----------------+---------------------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------");
        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", getProductID(),
                getProductName(), getUnit(), getQuantity(), getPrice(), null, null, null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
