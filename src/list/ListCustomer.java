package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import control.*;
import element.*;

public class ListCustomer implements InterfaceList<Customer> {
    private Customer[] lsctm;
    private int n;

    private String customerID;

    public ListCustomer() {
        n = 0;
        lsctm = new Customer[n];
        readFromFile();
    }

    public ListCustomer(ListCustomer other) {
        this.n = other.n;
        this.lsctm = Arrays.copyOf(other.lsctm, n);
        readFromFile();
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int length() {
        return n;
    }

    public Customer getCustomer(int i) {
        return lsctm[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so luong khach hang: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        Static.clearScreen();

        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-15s%n", "Ma khach hang", "Ho khach hang",
                "Ten khach hang", "Dia chi", "So dien thoai");
        for (Customer i : lsctm) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListCustomer.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Customer i : lsctm) {
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
            FileReader fr = new FileReader("../src/data_base/ListCustomer.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 5) {
                    String customerID = txt[0].trim();
                    String firstName = txt[1].trim();
                    String lastName = txt[2].trim();
                    String address = txt[3].trim();
                    String phone = txt[4].trim();

                    add(new Customer(customerID, firstName, lastName, address, phone));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createCustomerID() {
        String customerID;
        do {
            customerID = "CTM" + Static.randomID();
        } while (search(customerID) != -1);

        return customerID;
    }

    public String inputCustomerID() {
        String input;
        while (true) {
            System.out.print("Nhap ten hoac ma khach hang: ");
            input = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (search(input) != -1) {
                break;
            }
            if ((input = searchByFullName(input)) != null) {
                break;
            }
            System.out.println("Ten hoac ma khach hang khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        lsctm = Arrays.copyOf(lsctm, n + 1);
        System.out.println("Thong tin khach hang: ");
        lsctm[n] = new Customer();
        lsctm[n].setCustomerID(customerID);
        lsctm[n++].input();
        writeToFile(false);
    }

    public void add(Customer x) {
        lsctm = Arrays.copyOf(lsctm, n + 1);
        lsctm[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String customerID = inputCustomerID();
        int index = search(customerID);
        search(index);

        Static.exit();
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma khach hang khong dung!");
            return;
        }

        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-15s%n", "Ma khach hang", "Ho khach hang",
                "Ten khach hang", "Dia chi", "So dien thoai");
        lsctm[index].display();
    }

    public int search(String customerID) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getCustomerID()).equals(customerID)) {
                return i;
            }
        }
        return -1;
    }

    public String searchByFullName(String nameProduct) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getFullName()).equals(nameProduct)) {
                return lsctm[i].getCustomerID();
            }
        }
        return null;
    }

    public Customer searchCByCustomerID(String customerID) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getCustomerID()).equals(customerID)) {
                return lsctm[i];
            }
        }
        return null;
    }

    public Customer searchCByFirstName(String firstName) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getFirstName()).equals(firstName)) {
                return lsctm[i];
            }
        }
        return null;
    }

    public Customer searchCByLastName(String lastName) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getLastName()).equals(lastName)) {
                return lsctm[i];
            }
        }
        return null;
    }

    public Customer searchCByAddress(String address) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getAddress()).equals(address)) {
                return lsctm[i];
            }
        }
        return null;
    }

    public Customer searchCByPhone(String phone) {
        for (int i = 0; i < n; i++) {
            if ((lsctm[i].getPhone()).equals(phone)) {
                return lsctm[i];
            }
        }
        return null;
    }

    public Customer[] searchLsByCustomerID(String customerID) {
        int count = 0;
        Customer[] ls = new Customer[n];
        for (int i = 0; i < n; i++) {
            if (lsctm[i].getCustomerID().equals(customerID)) {
                ls[count++] = lsctm[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Customer[] searchLsByFirstName(String firstName) {
        int count = 0;
        Customer[] ls = new Customer[n];
        for (int i = 0; i < n; i++) {
            if (lsctm[i].getFirstName().equals(firstName)) {
                ls[count++] = lsctm[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Customer[] searchLsByLastName(String lastName) {
        int count = 0;
        Customer[] ls = new Customer[n];
        for (int i = 0; i < n; i++) {
            if (lsctm[i].getLastName().equals(lastName)) {
                ls[count++] = lsctm[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Customer[] searchLsByAddress(String address) {
        int count = 0;
        Customer[] ls = new Customer[n];
        for (int i = 0; i < n; i++) {
            if (lsctm[i].getAddress().equals(address)) {
                ls[count++] = lsctm[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Customer[] searchLsByPhone(String phone) {
        int count = 0;
        Customer[] ls = new Customer[n];
        for (int i = 0; i < n; i++) {
            if (lsctm[i].getPhone().equals(phone)) {
                ls[count++] = lsctm[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        String customerID = inputCustomerID();
        remove(customerID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma khach hang khong dung!");
            return;
        }

        for (int i = index; i < n - 1; i++) {
            lsctm[i] = lsctm[i + 1];
        }
        lsctm = Arrays.copyOf(lsctm, n - 1);
        n--;
        System.out.println("Xoa thanh cong!");

        writeToFile(false);
    }

    public void remove(String customerID) {
        int index = search(customerID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma khach hang khong dung!");
            return;
        }

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. Ho khach hang");
            System.out.println("2. Ten khach hang");
            System.out.println("3. Dia chi");
            System.out.println("4. So dien thoai");
            System.out.println("5. Thoat");
            System.out.print("Chon chuc nang (1-5): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsctm[index].setFirstName();
                    break;
                case 2:
                    lsctm[index].setLastName();
                    break;
                case 3:
                    lsctm[index].setAddress();
                    break;
                case 4:
                    lsctm[index].setPhone();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Khong hop le!");
            }
        } while (choice != 5);
        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String customerID = inputCustomerID();
        edit(customerID);
    }

    public void edit(String customerID) {
        int index = search(customerID);
        edit(index);
    }
}
