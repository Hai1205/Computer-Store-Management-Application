package element;

import control.Static;

public abstract class Product {
    private String productID, productName, unit;
    public int quantity, price;

    public Product() {
    }

    public Product(String productID, String productName, int quantity, int price, String unit) {
        setProductID(productID);
        setProductName(productName);
        setQuantity(quantity);
        setPrice(price);
        setUnit(unit);
    }

    public Product(Product other) {
        setProductID(other.productID);
        setProductName(other.productName);
        setQuantity(other.quantity);
        setPrice(other.price);
        setUnit(other.unit);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductName() {
        System.out.print("Nhap ten san pham: ");
        setProductName(Static.scanner.nextLine());
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrice() {
        System.out.print("Nhap gia ban: ");
        setPrice(Static.checkInputIsInt());
        Static.scanner.nextLine();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnit() {
        System.out.print("Nhap don vi tinh: ");
        setUnit(Static.scanner.nextLine());
    }

    public void input() {
        setProductName();
    }

    public abstract void display();

    @Override
    public String toString() {
        return productID + ", " + productName + ", " + quantity + ", " + price + ", " + unit;
    }

}