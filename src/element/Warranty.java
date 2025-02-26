package element;

import control.Static;

public class Warranty {
    private String warrantyID, productID, billID, customerID, datePurchase, dateEnd;
    private int warrantyTime;

    public Warranty() {
    }

    public Warranty(String warrantyID, String productID, String billID, String customerID, String datePurchase,
            int warrantyTime, String dateEnd) {
        setWarrantyID(warrantyID);
        setProductID(productID);
        setBillID(billID);
        setCustomerID(customerID);
        setDatePurchase(datePurchase);
        setWarrantyTime(warrantyTime);
        setDateEnd(dateEnd);
    }

    public Warranty(Warranty other) {
        setWarrantyID(other.warrantyID);
        setProductID(other.productID);
        setBillID(other.billID);
        setCustomerID(other.customerID);
        setDatePurchase(other.datePurchase);
        setWarrantyTime(other.warrantyTime);
        setDateEnd(other.dateEnd);
    }

    public String getWarrantyID() {
        return warrantyID;
    }

    public String getProductID() {
        return productID;
    }

    public String getBillID() {
        return billID;
    }

    public int getWarrantyTime() {
        return warrantyTime;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getDatePurchase() {
        return datePurchase;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public void setWarrantyTime(int warrantyTime) {
        this.warrantyTime = warrantyTime;
        if (datePurchase != null)
            setDateEnd();
    }

    public void setWarrantyTime() {
        System.out.print("Thoi gian bao hanh: ");
        setWarrantyTime(Static.checkInputIsInt());
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase;
        if (warrantyTime != 0)
            setDateEnd();
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateEnd() {
        setDateEnd(Static.plusMonth(datePurchase, warrantyTime));
    }

    public void input() {
        setWarrantyTime(12);
        setDatePurchase(Static.getCurrentDate());
    }

    public void display() {
        System.out.println(
                "-----------------+-----------------+-----------------+-----------------+-----------------+----------------------+---------------------");
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", warrantyID, productID, billID,
                customerID, datePurchase, warrantyTime, dateEnd);
    }

    @Override
    public String toString() {
        return warrantyID + ", " + productID + ", " + billID + ", " + customerID + ", " + datePurchase + ", "
                + warrantyTime + ", " + dateEnd;
    }
}
