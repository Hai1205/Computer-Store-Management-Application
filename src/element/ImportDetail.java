package element;

import control.Static;
import list.ListProduct;

public class ImportDetail {

    private String importID, productID;
    private int quantity, price, cost;
    private ListProduct lspd;

    public ImportDetail() {
    }

    public ImportDetail(String importID, String productID, int quantity, int price, int cost) {
        setImportID(importID);
        setProductID(productID);
        setQuantity(quantity);
        setPrice(price);
        setCost(cost);
    }

    public ImportDetail(ImportDetail other) {
        setImportID(other.importID);
        setProductID(other.productID);
        setQuantity(other.quantity);
        setPrice(other.price);
        setCost(other.cost);
    }

    public void setListProduct(ListProduct lspd) {
        this.lspd = lspd;
    }

    public void setImportID(String importID) {
        this.importID = importID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductID() {
        System.out.print("Nhap ten hoac ma san pham: ");
        String input = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");

        if (Static.checkSpace(input)) {
            setProductID(lspd.searchByProductName(input));
        } else if (lspd.search(input) != -1) {
            setProductID(input);
        }
        if (productID == null) {
            setProductID(lspd.createProductID());
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setCost();
    }

    public void setQuantity() {
        System.out.print("Nhap so luong mua: ");
        setQuantity(Static.checkInputIsInt());
        Static.scanner.nextLine();
    }

    public void setPrice(int price) {
        this.price = price;
        setCost();
    }

    public void setPrice() {
        System.out.print("Nhap gia mua: ");
        setPrice(Static.checkInputIsInt());
        Static.scanner.nextLine();
    }

    public void setCost() {
        setCost(quantity * price);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImportID() {
        return importID;
    }

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }

    public void input() {
        setProductID();

        setQuantity();

        setPrice();

        setCost();
    }

    public void display() {
        System.out.println(
                "----------------------+-----------------+----------------------+----------------------+----------------");
        System.out.format(" %-20s | %-15s | %-20s | %-20s | %-15s%n", importID, productID, quantity, price, cost);
    }

    @Override
    public String toString() {
        return importID + ", " + productID + ", " + quantity + ", " + price + ", " + cost;
    }
}
