package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;

import control.*;
import element.*;

public class ListWarranty implements InterfaceList<Warranty> {
    private Warranty[] lswrt;
    private int n;

    public String warrantyID, productID, billID, customerID;

    public ListWarranty() {
        n = 0;
        lswrt = new Warranty[n];
        readFromFile();
    }

    public ListWarranty(ListWarranty other) {
        this.n = other.n;
        this.lswrt = Arrays.copyOf(other.lswrt, n);
        readFromFile();
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

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int length() {
        return n;
    }

    public Warranty getWarranty(int i) {
        return lswrt[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("So luong phieu bao hanh: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        Static.clearScreen();

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", "Ma bao hanh", "Ma san pham",
                "Ma hoa don",
                "Ma khach hang", "Ngay mua", "Thoi gian bao hanh", "Ngay het bao hanh");
        for (Warranty i : lswrt) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListWarranty.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Warranty i : lswrt) {
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
            FileReader fr = new FileReader("../src/data_base/ListWarranty.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 7) {
                    String warrantyID = txt[0].trim();
                    String productID = txt[1].trim();
                    String billID = txt[2].trim();
                    String customerID = txt[3].trim();
                    String datePurchase = txt[4].trim();
                    int warrantyTime = Integer.parseInt(txt[5].trim());
                    String dateEnd = txt[6].trim();
                    add(new Warranty(warrantyID, productID, billID, customerID, datePurchase, warrantyTime, dateEnd));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createWarrantyID() {
        String warrantyID;
        do {
            warrantyID = "WRT" + Static.randomID();
        } while (search(warrantyID) != -1);

        return warrantyID;
    }

    public String inputWarrantyID() {
        String input;
        while (true) {
            System.out.print("Nhap ma phieu bao hanh: ");
            input = Static.inputStringNoSpace();
            if (search(input) != -1) {
                break;
            }
            System.out.println("Ma phieu bao hanh khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        lswrt = Arrays.copyOf(lswrt, n + 1);

        lswrt[n] = new Warranty(warrantyID, productID, billID, customerID, null, 0,
                null);
        lswrt[n++].input();

        writeToFile(false);
    }

    public void add(Warranty x) {
        lswrt = Arrays.copyOf(lswrt, n + 1);
        lswrt[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String warrantyID = inputWarrantyID();
        int index = search(warrantyID);
        if (index == -1 || !lswrt[index].getCustomerID().equals(customerID)) {
            System.out.println("Phieu bao hanh khong ton tai hoac khong thuoc ve tai khoan nay!");
            Static.exit();
            return;
        }

        search(index);

        Static.exit();
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma phieu bao hanh khong dung!");
            return;
        }

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", "Ma bao hanh", "Ma san pham",
                "Ma hoa don",
                "Ma khach hang", "Ngay mua", "Thoi gian bao hanh", "Ngay het bao hanh");
        lswrt[index].display();
    }

    public int search(String warrantyID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(warrantyID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchByBillID(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getBillID()).equals(billID)) {
                return i;
            }
        }
        return -1;
    }

    public Warranty searchWByWarrantyID(String warrantyID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(warrantyID)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByProductID(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(productID)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByBillID(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(billID)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByCustomerID(String customerID) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(customerID)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByWarrantyTime(String warrantyTime) {
        for (int i = 0; i < n; i++) {
            if ((lswrt[i].getWarrantyID()).equals(warrantyTime)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByDateEnd(String date) {
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getDateEnd().equals(date)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty searchWByDatePurchase(String date) {
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getDatePurchase().equals(date)) {
                return lswrt[i];
            }
        }
        return null;
    }

    public Warranty[] searchLsByWarrantyID(String warrantyID) {
        int count = 0;
        Warranty[] ls = new Warranty[n];
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getWarrantyID().equals(warrantyID)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByProductID(String productID) {
        int count = 0;
        Warranty[] ls = new Warranty[n];
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getProductID().equals(productID)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByBillID(String billID) {
        int count = 0;
        Warranty[] ls = new Warranty[n];
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getBillID().equals(billID)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByCustomerID(String customerID) {
        int count = 0;
        Warranty[] ls = new Warranty[n];
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getCustomerID().equals(customerID)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByWarrantyTime(int warrantyTime) {
        int count = 0;
        Warranty[] ls = new Warranty[n];
        for (int i = 0; i < n; i++) {
            if (lswrt[i].getWarrantyTime() == warrantyTime) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByDatePurchase(String date) {
        int count = 0;
        Warranty[] ls = new Warranty[n];

        for (int i = 0; i < n; i++) {
            if (lswrt[i].getDatePurchase().equals(date)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Warranty[] searchLsByDateEnd(String date) {
        int count = 0;
        Warranty[] ls = new Warranty[n];

        for (int i = 0; i < n; i++) {
            if (lswrt[i].getDateEnd().equals(date)) {
                ls[count++] = lswrt[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void lsUser() {
        Static.clearScreen();

        Warranty[] ls = searchLsByCustomerID(customerID);
        if (ls == null) {
            System.out.println("Chua co phieu bao hanh");
            Static.exit();
            return;
        }
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", "Ma bao hanh", "Ma san pham",
                "Ma hoa don",
                "Ma khach hang", "Ngay mua", "Thoi gian bao hanh", "Ngay het bao hanh");
        for (Warranty i : ls) {
            i.display();
        }
        Static.exit();
    }

    public void lsDateRemain() {
        Static.clearScreen();

        Warranty[] ls = searchLsByCustomerID(customerID);
        Warranty[] lsex = new Warranty[ls.length];
        int count = 0;

        for (int i = 0; i < ls.length; i++) {
            LocalDate date = LocalDate.parse(ls[i].getDateEnd(), Static.dateFormat);
            if (date.isAfter(LocalDate.now())) {
                lsex[count++] = ls[i];
            }
        }

        if (count == 0) {
            System.out.println("Tat ca phieu bao hanh deu da han su dung");
            Static.exit();
            return;
        }

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", "Ma bao hanh", "Ma san pham",
                "Ma hoa don",
                "Ma khach hang", "Ngay mua", "Thoi gian bao hanh", "Ngay het bao hanh");
        for (int i = 0; i < count; i++) {
            lsex[i].display();
        }

        Static.exit();
    }

    public void lsDateEnd() {
        Static.clearScreen();

        Warranty[] ls = searchLsByCustomerID(customerID);
        Warranty[] lse = new Warranty[ls.length];
        int count = 0;

        for (int i = 0; i < ls.length; i++) {
            LocalDate date = LocalDate.parse(ls[i].getDateEnd(), Static.dateFormat);
            if (date.isBefore(LocalDate.now())) {
                lse[count++] = ls[i];
            }
        }

        if (count == 0) {
            System.out.println("Tat ca phieu bao hanh deu con han su dung");
            Static.exit();
            return;
        }

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-15s | %-20s | %-20s%n", "Ma bao hanh", "Ma san pham",
                "Ma hoa don",
                "Ma khach hang", "Ngay mua", "Thoi gian bao hanh", "Ngay het bao hanh");
        for (int i = 0; i < count; i++) {
            lse[i].display();
        }

        Static.exit();
    }

    public void remove() {
        Static.clearScreen();

        String warrantyID = inputWarrantyID();
        remove(warrantyID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma phieu bao hanh khong dung!");
            return;
        }

        for (int i = index; i < n - 1; i++) {
            lswrt[i] = lswrt[i + 1];
        }
        lswrt = Arrays.copyOf(lswrt, n - 1);
        n--;
        System.out.println("Xoa thanh cong!");

        writeToFile(false);
    }

    public void remove(String warrantyID) {
        int index = search(warrantyID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma phieu bao hanh khong dung!");
            return;
        }

        lswrt[index].setWarrantyTime();
        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String warrantyID = inputWarrantyID();
        edit(warrantyID);
    }

    public void edit(String warrantyID) {
        int index = search(warrantyID);
        edit(index);
    }
}
