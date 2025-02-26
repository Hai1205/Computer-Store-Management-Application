package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import control.*;
import element.*;

public class ListProducer implements InterfaceList<Producer> {
    private Producer[] lspd;
    private int n;

    private String producerID;

    public ListProducer() {
        n = 0;
        lspd = new Producer[n];
        readFromFile();
    }

    public ListProducer(ListProducer other) {
        this.n = other.n;
        this.lspd = Arrays.copyOf(other.lspd, n);
        readFromFile();
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public int length() {
        return n;
    }

    public Producer getProducerID(int i) {
        return lspd[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so luong nha san xuat: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        Static.clearScreen();

        System.out.format(" %-15s | %-20s%n", "Ma nha san xuat", "Ten nha san xuat");
        for (Producer i : lspd) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListProducer.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Producer i : lspd) {
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
            FileReader fr = new FileReader("../src/data_base/ListProducer.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 2) {
                    String producerID = txt[0].trim();
                    String productName = txt[1].trim();
                    add(new Producer(producerID, productName));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createProducerID() {
        String producerID;
        do {
            producerID = "PDC" + Static.randomID();
        } while (search(producerID) != -1);

        return producerID;
    }

    public String inputProducerID() {
        String input;
        while (true) {
            System.out.print("Nhap ten hoac ma nha san xuat: ");
            input = Static.inputStringNoSpace();
            if (search(input) != -1) {
                break;
            }
            if ((input = searchByProducerName(input)) != null) {
                break;
            }
            System.out.println("Ten hoac ma nha san xuat khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        lspd = Arrays.copyOf(lspd, n + 1);
        System.out.println("Thong tin nha san xuat: ");
        lspd[n] = new Producer();

        if (producerID == null || producerID.isEmpty()) {
            producerID = createProducerID();
        }

        lspd[n].setProducerID(producerID);
        lspd[n++].input();

        writeToFile(false);

        Static.exit();
    }

    public void add(Producer x) {
        lspd = Arrays.copyOf(lspd, n + 1);
        lspd[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String producerID = inputProducerID();
        int index = search(producerID);
        search(index);

        Static.exit();
    }

    public String searchByProducerName(String producerName) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProducerName()).equals(producerName)) {
                return lspd[i].getProducerID();
            }
        }
        return null;
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma nha san xuat khong dung!");
            return;
        }

        System.out.format(" %-15s | %-20s%n", "Ma nha san xuat", "Ten nha san xuat");
        lspd[index].display();
    }

    public int search(String producerID) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProducerID()).equals(producerID)) {
                return i;
            }
        }
        return -1;
    }

    public Producer searchPByProducerID(String producerID) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProducerID()).equals(producerID)) {
                return lspd[i];
            }
        }
        return null;
    }

    public Producer searchPByCompanyName(String productName) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProducerName()).equals(productName)) {
                return lspd[i];
            }
        }
        return null;
    }

    public Producer[] searchLsByProducerID(String producerID) {
        int count = 0;
        Producer[] ls = new Producer[n];
        for (int i = 0; i < n; i++) {
            if (lspd[i].getProducerID().equals(producerID)) {
                ls[count++] = lspd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Producer[] searchLsByCompanyName(String productName) {
        int count = 0;
        Producer[] ls = new Producer[n];
        for (int i = 0; i < n; i++) {
            if (lspd[i].getProducerName().equals(productName)) {
                ls[count++] = lspd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        String producerID = inputProducerID();
        remove(producerID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma nha san xuat khong dung!");
            return;
        }

        for (int i = index; i < n - 1; i++) {
            lspd[i] = lspd[i + 1];
        }
        lspd = Arrays.copyOf(lspd, n - 1);
        n--;
        System.out.println("Xoa thanh cong!");

        writeToFile(false);
    }

    public void remove(String producerID) {
        int index = search(producerID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma nha san xuat khong dung!");
            return;
        }

        lspd[index].setProducerName();

        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String producerID = inputProducerID();
        edit(producerID);
    }

    public void edit(String producerID) {
        int index = search(producerID);
        edit(index);
    }
}
