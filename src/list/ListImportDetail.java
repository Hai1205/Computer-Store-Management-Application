package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import control.*;
import element.*;

public class ListImportDetail implements InterfaceList<ImportDetail> {
    private ImportDetail[] lsipd;
    private int n;

    private ListProduct lspd;
    private String importID, producerID;

    public ListImportDetail() {
        n = 0;
        lsipd = new ImportDetail[n];
        readFromFile();
    }

    public ListImportDetail(ListProduct lspd) {
        n = 0;
        lsipd = new ImportDetail[n];
        this.lspd = lspd;
        readFromFile();
    }

    public ListImportDetail(ListImportDetail other) {
        this.n = other.n;
        this.lsipd = Arrays.copyOf(other.lsipd, n);
        readFromFile();
    }

    public void setListProduct(ListProduct lspd) {
        this.lspd = lspd;
    }

    public void setImportID(String importID) {
        this.importID = importID;
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public int length() {
        return n;
    }

    public ImportDetail getImportDetail(int i) {
        return lsipd[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so luong chi tiet phieu nhap hang: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        System.out.format(" %-20s | %-15s | %-20s | %-20s | %-15s%n", "Ma phieu nhap hang", "Ma san pham",
                "So luong nhap hang", "Don gia nhap hang", "Thanh tien");
        for (ImportDetail i : lsipd) {
            i.display();
        }
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListImportDetail.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);
            for (ImportDetail i : lsipd) {
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
            FileReader fr = new FileReader("../src/data_base/ListImportDetail.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 5) {
                    String importID = txt[0].trim();
                    String productID = txt[1].trim();
                    int quantity = Integer.parseInt(txt[2].trim());
                    int price = Integer.parseInt(txt[3].trim());
                    int cost = Integer.parseInt(txt[4].trim());
                    add(new ImportDetail(importID, productID, quantity, price, cost));
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

        lsipd = Arrays.copyOf(lsipd, n + 1);
        System.out.println("Thong tin chi tiet phieu nhap hang: ");
        lsipd[n] = new ImportDetail();
        lsipd[n].setListProduct(lspd);
        lsipd[n].setImportID(importID);
        lsipd[n].input();

        int price = lsipd[n].getPrice() + (int) (lsipd[n].getPrice() * 30 / 100);
        int index = lspd.search(lsipd[n].getProductID());
        if (index == -1) {
            lspd.setProductID(lsipd[n].getProductID());
            lspd.setQuantity(lsipd[n].getQuantity());
            lspd.setProducerID(producerID);
            lspd.setPrice(price);
            lspd.add();
        } else {
            lspd.increaseQuantity(lsipd[n].getProductID(), lsipd[n].getQuantity());
            lspd.getProduct(index).setPrice(price);
        }
        n++;

        lspd.writeToFile(false);
        writeToFile(false);
    }

    public void add(ImportDetail x) {
        lsipd = Arrays.copyOf(lsipd, n + 1);
        lsipd[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        System.out.print("Ma phieu nhap hang: ");
        String importID = Static.scanner.nextLine();
        int index = search(importID);
        search(index);
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma phieu nhap hang khong dung!");
            return;
        }

        System.out.format(" %-20s | %-15s | %-20s | %-20s | %-15s%n", "Ma phieu nhap hang", "Ma san pham",
                "So luong nhap hang", "Don gia nhap hang", "Thanh tien");
        lsipd[index].display();
    }

    public int search(String importID) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getImportID()).equals(importID)) {
                return i;
            }
        }
        return -1;
    }

    public ImportDetail searchIDByProductID(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getProductID()).equals(productID)) {
                return lsipd[i];
            }
        }
        return null;
    }

    public ImportDetail searchIDByQuantity(int quantity) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getQuantity()) == quantity) {
                return lsipd[i];
            }
        }
        return null;
    }

    public ImportDetail searchIDByPrice(int price) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getPrice()) == price) {
                return lsipd[i];
            }
        }
        return null;
    }

    public ImportDetail searchIDByCost(int cost) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getCost()) == cost) {
                return lsipd[i];
            }
        }
        return null;
    }

    public ImportDetail searchIDByImportID(String importID) {
        for (int i = 0; i < n; i++) {
            if ((lsipd[i].getImportID()).equals(importID)) {
                return lsipd[i];
            }
        }
        return null;
    }

    public ImportDetail[] searchLsByImportID(String importID) {
        int count = 0;
        ImportDetail[] ls = new ImportDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsipd[i].getImportID().equals(importID)) {
                ls[count++] = lsipd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ImportDetail[] searchLsByProductID(String productID) {
        int count = 0;
        ImportDetail[] ls = new ImportDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsipd[i].getProductID().equals(productID)) {
                ls[count++] = lsipd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ImportDetail[] searchLsByQuantity(int quantity) {
        int count = 0;
        ImportDetail[] ls = new ImportDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsipd[i].getQuantity() == quantity) {
                ls[count++] = lsipd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ImportDetail[] searchLsByPrice(int price) {
        int count = 0;
        ImportDetail[] ls = new ImportDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsipd[i].getPrice() == price) {
                ls[count++] = lsipd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ImportDetail[] searchLsByCost(int cost) {
        int count = 0;
        ImportDetail[] ls = new ImportDetail[n];
        for (int i = 0; i < n; i++) {
            if (lsipd[i].getCost() == cost) {
                ls[count++] = lsipd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        System.out.print("Ma phieu nhap hang: ");
        String importID = Static.scanner.nextLine();
        remove(importID);
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma phieu nhap hang khong dung!");
            return;
        }

        lspd.reduceQuantity(lsipd[index].getProductID(), lsipd[index].getQuantity());

        for (int i = index; i < n - 1; i++) {
            lsipd[i] = lsipd[i + 1];
        }
        lsipd = Arrays.copyOf(lsipd, n - 1);
        n--;

        writeToFile(false);
    }

    public void remove(String importID) {
        int index = search(importID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma phieu nhap hang khong dung!");
            return;
        }

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. So luong");
            System.out.println("2. Don gia");
            System.out.println("3. Thoat");
            System.out.print("Chon chuc nang (1-3): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("So luong: ");
                    int quantity = Static.scanner.nextInt();
                    lsipd[index].setQuantity(quantity);
                    break;
                case 2:
                    System.out.println("Don gia: ");
                    int price = Static.scanner.nextInt();
                    lsipd[index].setPrice(price);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("khong hop le!");
            }

        } while (choice != 4);
        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        System.out.print("Ma phieu nhap hang: ");
        String importID = Static.scanner.nextLine();
        edit(importID);
    }

    public void edit(String importID) {
        int index = search(importID);
        edit(index);
    }
}
