package element;

import control.Static;

public class LapTop extends Product {
    private String pin, type;

    public LapTop() {
    }

    public LapTop(String productID, String productName, int quantity, int price, String unit, String pin, String type) {
        super(productID, productName, quantity, price, unit);
        setPin(pin);
        setType(type);
    }

    public LapTop(LapTop other) {
        super((Product) other);
        setPin(other.pin);
        setType(other.type);
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setPin() {
        System.out.print("Nhap dung luong pin: ");
        setPin(Static.scanner.nextLine());
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType() {
        System.out.print("Nhap loai may: ");
        setType(Static.scanner.nextLine());
    }

    public String getPin() {
        return pin;
    }

    public String getType() {
        return type;
    }

    @Override
    public void input() {
        super.input();
        setPin();

        setType();
    }

    @Override
    public void display() {
        System.out.println(
                "-----------------+---------------------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------");
        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", getProductID(),
                getProductName(), getUnit(), getQuantity(), getPrice(), pin, type, null);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + pin + ", " + type;
    }
}