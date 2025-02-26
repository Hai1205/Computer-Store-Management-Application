package element;

import control.Static;

public class PC extends Product {
    private String nameScreen;

    public PC() {
    }

    public PC(String productID, String productName, int quantity, int price, String unit, String nameScreen) {
        super(productID, productName, quantity, price, unit);
        setNameScreen(nameScreen);
    }

    public PC(PC other) {
        super((Product) other);
        setNameScreen(other.nameScreen);
    }

    public void setNameScreen(String nameScreen) {
        this.nameScreen = nameScreen;
    }

    public void setNameScreen() {
        System.out.print("Nhap ten man hinh: ");
        setNameScreen(Static.scanner.nextLine());
    }

    public String getNameScreen() {
        return nameScreen;
    }

    @Override
    public void input() {
        super.input();
        setNameScreen();
    }

    @Override
    public void display() {
        System.out.println(
                "-----------------+---------------------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------");
        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", getProductID(),
                getProductName(), getUnit(), getQuantity(), getPrice(), null, null, nameScreen);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + nameScreen;
    }
}