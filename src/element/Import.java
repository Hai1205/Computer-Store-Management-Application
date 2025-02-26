package element;

import control.Static;
import list.ListImportDetail;

public class Import {
    private ListImportDetail lsid;

    private String importID, employeeID, producerID, date;
    private int totalCost;

    public Import() {
    }

    public Import(String importID, String employeeID, String producerID, String date, int totalCost) {
        setImportID(importID);
        setEmployeeID(employeeID);
        setProducerID(producerID);
        setDate(date);
        setTotalCost(totalCost);
    }

    public Import(Import other) {
        setImportID(other.importID);
        setEmployeeID(other.employeeID);
        setProducerID(other.producerID);
        setDate(other.date);
        setTotalCost(other.totalCost);
    }

    public void setlsid(ListImportDetail lsid) {
        this.lsid = lsid;
    }

    public void setImportID(String importID) {
        this.importID = importID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate() {
        System.out.print("Nhap ngay nhap hang: ");
        setDate(Static.inputDate());
    }

    public void setTotalCost() {
        int total = 0;

        ImportDetail[] ls = lsid.searchLsByImportID(importID);

        for (ImportDetail id : ls) {
            total += id.getCost();
        }
        setTotalCost(total);
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getImportID() {
        return importID;
    }

    public String getDate() {
        return date;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getProducerID() {
        return producerID;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void input() {
        setDate(Static.getCurrentDate());

        if (totalCost != 0) {
            setTotalCost();
        }
    }

    public void display() {
        System.out.println(
                "----------------------+----------------------+----------------------+----------------------+-------------------------");
        System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", importID, employeeID, producerID, date,
                totalCost);
    }

    @Override
    public String toString() {
        return importID + ", " + employeeID + ", " + producerID + ", " + date + ", " + totalCost;
    }
}
