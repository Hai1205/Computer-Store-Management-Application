package element;

import control.Static;
import list.ListProduct;

public class BillDetail {
    private ListProduct lspd;

    private String billID, productID, warrantyID;
    private int quantity, price, cost;

    public BillDetail() {
    }

    public BillDetail(String billID, String productID, String warrantyID, int quantity, int price, int cost) {
        setBillID(billID);
        setProductID(productID);
        setWarrantyID(warrantyID);
        setQuantity(quantity);
        setPrice(price);
        setCost(cost);
    }

    public BillDetail(BillDetail other) {
        setBillID(other.billID);
        setProductID(other.productID);
        setWarrantyID(other.warrantyID);
        setQuantity(other.quantity);
        setPrice(other.price);
        setCost(other.cost);
    }

    public String getBillID() {
        return billID;
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

    public String getWarrantyID() {
        return warrantyID;
    }

    public void setListProduct(ListProduct lspd) {
        this.lspd = lspd;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setCost();
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrice() {
        int index = lspd.search(this.productID);
        setPrice(lspd.getProduct(index).getPrice());
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCost() {
        setCost(this.quantity * this.price);
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    public void input() {
        System.out.print("Nhap so luong mua: ");
        setQuantity(Static.checkInputIsInt());
        Static.scanner.nextLine();

        setPrice();

        setCost();
    }

    public void display() {
        System.out.println(
                "-----------------+-----------------+-----------------+-----------------+------------+----------------");
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-10s | %-15s%n", billID, productID, warrantyID, quantity,
                price, cost);
    }

    @Override
    public String toString() {
        return billID + ", " + productID + ", " + warrantyID + ", " + quantity + ", " + price + ", " + cost;
    }
}
