package element;

import control.Static;
import list.*;

public class Bill {
    private ListBillDetail lsbd;
    private String billID, employeeID, customerID, date;
    public int totalCost;

    public Bill() {
    }

    public Bill(String billID, String employeeID, String customerID, String date, int totalCost) {
        setBillID(billID);
        setEmployeeID(employeeID);
        setCustomerID(customerID);
        setDate(date);
        setTotalCost(totalCost);
    }

    public Bill(Bill other) {
        setBillID(other.billID);
        setEmployeeID(other.employeeID);
        setCustomerID(other.customerID);
        setDate(other.date);
        setTotalCost(other.totalCost);
    }

    public String getBillID() {
        return billID;
    }

    public String getDate() {
        return date;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setlsod(ListBillDetail lsbd) {
        this.lsbd = lsbd;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate() {
        System.out.print("Nhap ngay lap hoa don: ");
        setDate(Static.inputDate());
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setTotalCost() {
        int total = 0;

        BillDetail[] ls = lsbd.searchLsByBillID(this.billID);

        for (BillDetail od : ls) {
            total += od.getCost();
        }

        setTotalCost(total);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void input() {
        setDate(Static.getCurrentDate());

        setTotalCost();
    }

    public void display() {
        System.out.println(
                "-----------------+-----------------+-----------------+-----------------+---------------------");
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", billID, employeeID, customerID, date, totalCost);
    }

    @Override
    public String toString() {
        return billID + ", " + employeeID + ", " + customerID + ", " + date + ", " + totalCost;
    }
}