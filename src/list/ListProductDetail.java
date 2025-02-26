package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import control.*;
import element.*;

public class ListProductDetail implements InterfaceList<ProductDetail> {
    private ProductDetail[] lspdd;
    private int n;

    private String productID, producerID;

    public ListProductDetail() {
        n = 0;
        lspdd = new ProductDetail[n];
        readFromFile();
    }

    public ListProductDetail(ListProductDetail other) {
        this.n = other.n;
        this.lspdd = Arrays.copyOf(other.lspdd, n);
        readFromFile();
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public int length() {
        return n;
    }

    public ProductDetail getProductDetail(int i) {
        return lspdd[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("So luong chi tiet san pham: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        System.out.format(" %-15s | %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n", "Ma san pham",
                "Ma nha san xuat", "Dung luong(RAM)", "Dung luong(ROM)", "Xung nhip(CPU)", "Xung nhip(VGA)",
                "He dieu hanh", "Nam san xuat");
        for (ProductDetail i : lspdd) {
            i.display();
        }
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListProductDetail.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);
            for (ProductDetail i : lspdd) {
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
            FileReader fr = new FileReader("../src/data_base/ListProductDetail.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 8) {
                    String productID = txt[0].trim();
                    String producerID = txt[1].trim();
                    String RAM = txt[2].trim();
                    String ROM = txt[3].trim();
                    String CPU = txt[4].trim();
                    String VGA = txt[5].trim();
                    String OS = txt[6].trim();
                    int yearProduce = Integer.parseInt(txt[7].trim());
                    add(new ProductDetail(productID, producerID, RAM, ROM, CPU, VGA, OS, yearProduce));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select() {
        Static.clearScreen();

        lspdd = Arrays.copyOf(lspdd, n + 1);
        lspdd[n] = new ProductDetail(productID, producerID, null, null, null, null, null, 0);

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. RAM");
            System.out.println("2. CPU");
            System.out.println("3. ROM");
            System.out.println("4. VGA");
            System.out.print("Chon loai san pham (1-4): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspdd[n].setRAM();
                    break;
                case 2:
                    lspdd[n].setCPU();
                    break;
                case 3:
                    lspdd[n].setROM();
                    break;
                case 4:
                    lspdd[n].setVGA();
                    break;
                default:
                    System.out.println("khong hop le!");
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);

        lspdd[n++].setYearProduce();

        writeToFile(false);
    }

    public void add() {
        Static.clearScreen();

        lspdd = Arrays.copyOf(lspdd, n + 1);
        System.out.println("Thong tin chi tiet san pham: ");
        lspdd[n] = new ProductDetail(productID, producerID, "", "", "", "", "", 0);
        lspdd[n++].input();

        writeToFile(false);
    }

    public void add(ProductDetail x) {
        lspdd = Arrays.copyOf(lspdd, n + 1);
        lspdd[n++] = x;
        writeToFile(false);
    }

    public void search() {
        System.out.print("Ma san pham: ");
        String productID = Static.scanner.nextLine();
        int index = search(productID);
        search(index);
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        System.out.println("+ Chi tiet san pham: ");
        System.out.format(" %-15s | %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-20s%n", "Ma san pham",
                "Ma nha san xuat", "Dung luong(RAM)", "Dung luon(ROM)", "Xung nhip(CPU)", "Xung nhip(VGA)",
                "He dieu hanh", "Nam san xuat");
        lspdd[index].display();
    }

    public int search(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getProductID()).equals(productID)) {
                return i;
            }
        }
        return -1;
    }

    public ProductDetail searchPDByProductID(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getProductID()).equals(productID)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByProducerID(String producerID) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getProducerID()).equals(producerID)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByRAM(String RAM) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getRAM()).equals(RAM)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByCPU(String CPU) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getCPU()).equals(CPU)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByROM(String ROM) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getROM()).equals(ROM)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByVGA(String VGA) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getVGA()).equals(VGA)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByOS(String OS) {
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getOS()).equals(OS)) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail searchPDByYearProduce(int yearProduce) {
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getYearProduce() == yearProduce) {
                return lspdd[i];
            }
        }
        return null;
    }

    public ProductDetail[] searchLsByProductID(String productID) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getProductID().equals(productID)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByProducerID(String producerID) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getProducerID().equals(producerID)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByRAM(String RAM) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getRAM().equals(RAM)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByCPU(String CPU) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getCPU().equals(CPU)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByROM(String ROM) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getROM().equals(ROM)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByVGA(String VGA) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if ((lspdd[i].getVGA()).equals(VGA)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByOS(String OS) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getOS().equals(OS)) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public ProductDetail[] searchLsByYearProduce(int yearProduce) {
        int count = 0;
        ProductDetail[] ls = new ProductDetail[n];
        for (int i = 0; i < n; i++) {
            if (lspdd[i].getYearProduce() == yearProduce) {
                ls[count++] = lspdd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        System.out.print("Ma san pham: ");
        String productID = Static.scanner.nextLine();
        remove(productID);
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        for (int i = index; i < n - 1; i++) {
            lspdd[i] = lspdd[i + 1];
        }
        lspdd = Arrays.copyOf(lspdd, n - 1);
        n--;
        System.out.println("Xoa thanh cong!");

        writeToFile(false);
    }

    public void remove(String productID) {
        int index = search(productID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. Dung luong(RAM)");
            System.out.println("2. Xung nhip(CPU)");
            System.out.println("3. Dung luong(ROM)");
            System.out.println("4. Xung nhip(VGA)");
            System.out.println("5. He dieu hanh");
            System.out.println("6. Nam san xuat");
            System.out.println("7. Thoat");
            System.out.print("Chon chuc nang (1-7): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspdd[index].setRAM();
                    break;
                case 2:
                    lspdd[index].setCPU();
                    break;
                case 3:
                    lspdd[index].setROM();
                    break;
                case 4:
                    lspdd[index].setVGA();
                    break;
                case 5:
                    lspdd[index].setOS();
                    break;
                case 6:
                    lspdd[index].setYearProduce();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("khong hop le!");
            }
        } while (choice != 7);
        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        System.out.print("Ma san pham: ");
        String productID = Static.scanner.nextLine();
        edit(productID);
    }

    public void edit(String productID) {
        int index = search(productID);
        edit(index);
    }
}
