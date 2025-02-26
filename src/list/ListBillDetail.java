package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import control.*;
import element.*;

public class ListBillDetail implements InterfaceList<BillDetail> {
    private BillDetail[] lsbd;
    private int n;

    private String billID, customerID;

    private ListProduct lspd;
    private ListWarranty lswrt;

    public ListBillDetail() {
        n = 0;
        lsbd = new BillDetail[n];
        readFromFile();
    }

    public ListBillDetail(ListProduct lspd, ListWarranty lswrt) {
        n = 0;
        lsbd = new BillDetail[n];
        setListProduct(lspd);
        setListWarranty(lswrt);
        readFromFile();
    }

    public ListBillDetail(ListBillDetail other) {
        this.n = other.n;
        this.lsbd = Arrays.copyOf(other.lsbd, n);
        readFromFile();
    }

    public void setListProduct(ListProduct lspd) {
        this.lspd = lspd;
    }

    public void setListWarranty(ListWarranty lswrt) {
        this.lswrt = lswrt;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int length() {
        return n;
    }

    public BillDetail getBillDetail(int i) {
        return lsbd[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("So luong chi tiet hoa don: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-10s | %-15s%n", "Ma hoa don", "Ma san pham",
                "Ma bao hanh", "So luong mua", "Don gia", "Thanh tien");
        for (BillDetail i : lsbd) {
            i.display();
        }
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListBillDetail.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (BillDetail i : lsbd) {
                bw.write(i.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileReader fr = new FileReader("../src/data_base/ListBillDetail.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 6) {
                    String billID = txt[0].trim();
                    String productID = txt[1].trim();
                    String warrantyID = txt[2].trim();
                    int quantity = Integer.parseInt(txt[3].trim());
                    int price = Integer.parseInt(txt[4].trim());
                    int cost = Integer.parseInt(txt[5].trim());
                    add(new BillDetail(billID, productID, warrantyID, quantity, price, cost));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        Static.clearScreen();

        lsbd = Arrays.copyOf(lsbd, n + 1);
        System.out.println("Thong tin chi tiet hoa don: ");

        String productID, warrantyID;
        productID = lspd.inputProductID();
        warrantyID = lswrt.createWarrantyID();

        lsbd[n] = new BillDetail(billID, productID, warrantyID, 0, 0, 0);
        lsbd[n].setListProduct(lspd);
        lsbd[n].input();

        lswrt.setWarrantyID(warrantyID);
        lswrt.setProductID(productID);
        lswrt.setBillID(billID);
        lswrt.setCustomerID(customerID);
        lswrt.add();

        lspd.reduceQuantity(productID, lsbd[n++].getQuantity());

        writeToFile(false);
    }

    public void add(BillDetail x) {
        lsbd = Arrays.copyOf(lsbd, n + 1);
        lsbd[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        System.out.print("Ma hoa don: ");
        String billID = Static.scanner.nextLine();
        int index = search(billID);
        search(index);
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
        }

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-10s | %-15s%n", "Ma hoa don", "Ma san pham",
                "Ma bao hanh", "So luong mua", "Don gia", "Thanh tien");
        lsbd[index].display();
    }

    public int search(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getBillID()).equals(billID)) {
                return i;
            }
        }
        return -1;
    }

    public BillDetail searchODByBillID(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getBillID()).equals(billID)) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail searchODByProductID(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getProductID()).equals(productID)) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail searchODByWarrantyID(String warrantyID) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getWarrantyID()).equals(warrantyID)) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail searchODByQuantity(int quantity) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getQuantity()) == quantity) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail searchODByPrice(int price) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getPrice()) == price) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail searchODByCost(int cost) {
        for (int i = 0; i < n; i++) {
            if ((lsbd[i].getCost()) == cost) {
                return lsbd[i];
            }
        }
        return null;
    }

    public BillDetail[] searchLsByBillID(String billID) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getBillID().equals(billID)) {
                ls[count++] = lsbd[i];
            }
        }

        return Arrays.copyOf(ls, count);
    }

    public BillDetail[] searchLsByProductID(String productID) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getProductID().equals(productID)) {
                ls[count++] = lsbd[i];
            }
        }

        return Arrays.copyOf(ls, count);
    }

    public BillDetail[] searchLsByWarrantyID(String warrantyID) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getWarrantyID().equals(warrantyID)) {
                ls[count++] = lsbd[i];
            }
        }

        return Arrays.copyOf(ls, count);
    }

    public BillDetail[] searchLsByQuantity(int quantity) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getQuantity() == quantity) {
                ls[count++] = lsbd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public BillDetail[] searchLsByPrice(int price) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getPrice() == price) {
                ls[count++] = lsbd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public BillDetail[] searchLsByCost(int cost) {
        int count = 0;
        BillDetail[] ls = new BillDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsbd[i].getCost() == cost) {
                ls[count++] = lsbd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        System.out.print("Ma hoa don: ");
        String billID = Static.scanner.nextLine();

        remove(billID);
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
            return;
        }

        lspd.increaseQuantity(lsbd[index].getProductID(), lsbd[index].getQuantity());

        int i;
        while ((i = lswrt.search(lsbd[index].getWarrantyID())) != -1) {
            lswrt.remove(i);
        }

        for (i = index; i < n - 1; i++) {
            lsbd[i] = lsbd[i + 1];
        }
        lsbd = Arrays.copyOf(lsbd, n - 1);
        n--;

        writeToFile(false);
    }

    public void remove(String billID) {
        int index = search(billID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
        }

        System.out.print("So luong mua: ");
        int quantity = Static.scanner.nextInt();
        lsbd[index].setQuantity(quantity);

        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        System.out.print("Ma hoa don: ");
        String billID = Static.scanner.nextLine();
        edit(billID);
    }

    public void edit(String billID) {
        int index = search(billID);
        edit(index);
    }
}
