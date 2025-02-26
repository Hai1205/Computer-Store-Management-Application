package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import control.*;
import element.*;

public class ListImport implements InterfaceList<Import> {
    private Import[] lsip;
    private int n;

    private ListProduct lspd;
    private ListImportDetail lsimd;
    private ListProducer lspdc;
    private ListEmployee lsem;

    public ListImport() {
        n = 0;
        lsip = new Import[n];
        readFromFile();
    }

    public ListImport(ListProduct lspd, ListImportDetail lsimd, ListProducer lspdc, ListEmployee lsem) {
        n = 0;
        lsip = new Import[n];
        this.lspd = lspd;
        this.lsimd = lsimd;
        this.lspdc = lspdc;
        this.lsem = lsem;
        readFromFile();
    }

    public ListImport(ListImport other) {
        this.n = other.n;
        this.lsip = Arrays.copyOf(other.lsip, n);
        readFromFile();
    }

    public int length() {
        return n;
    }

    public Import getImport(int i) {
        return lsip[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so luong phieu nhap hang: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", "Ma phieu nhap", "Ma nhan vien",
                "Ma nha san xuat", "Ngay nhap hang", "Tong tien/don nhap hang");
        for (Import i : lsip) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListImport.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Import i : lsip) {
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
            FileReader fr = new FileReader("../src/data_base/ListImport.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 5) {
                    String importID = txt[0].trim();
                    String employeeID = txt[1].trim();
                    String producerID = txt[2].trim();
                    String date = txt[3].trim();
                    int totalCost = Integer.parseInt(txt[4].trim());
                    add(new Import(importID, employeeID, producerID, date, totalCost));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createImportID() {
        String importID;
        do {
            importID = "IP" + Static.randomID();
        } while (search(importID) != -1);

        return importID;
    }

    public String inputImportID() {
        String input;
        while (true) {
            System.out.print("Nhap ma phieu nhap hang: ");
            input = Static.inputStringNoSpace();
            if (search(input) != -1) {
                break;
            }
            System.out.println("Ma phieu nhap hang khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        lsip = Arrays.copyOf(lsip, n + 1);
        System.out.println("Thong tin phieu nhap hang: ");

        String importID, employeeID, producerID;
        importID = createImportID();
        employeeID = lsem.inputEmployeeID();
        producerID = lspdc.inputProducerID();

        lsip[n] = new Import(importID, employeeID, producerID, null, 0);
        lsip[n].setlsid(lsimd);
        lsip[n].input();

        lsimd.setListProduct(lspd);
        lsimd.setImportID(importID);
        lsimd.setProducerID(producerID);
        lsimd.input();

        lsip[n++].setTotalCost();

        System.out.println("Nhap hang thanh cong!");
        System.out.println("Co the doi tra san pham trong vong 7 ngay, tinh tu luc nhap hang");
        search(n - 1);

        writeToFile(false);

        Static.exit();
    }

    public void add(Import x) {
        lsip = Arrays.copyOf(lsip, n + 1);
        lsip[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String importID = inputImportID();
        int index = search(importID);
        search(index);

        Static.exit();
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma phieu nhap hang khong dung!");
            return;
        }

        System.out.println("- Don nhap hang:");
        System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", "Ma phieu nhap", "Ma nhan vien",
                "Ma nha san xuat", "Ngay nhap hang", "Tong tien/don nhap hang");
        lsip[index].display();

        System.out.println("+ Chi tiet phieu nhap hang: ");
        ImportDetail[] ls = lsimd.searchLsByImportID(lsip[index].getImportID());
        System.out.format(" %-20s | %-15s | %-20s | %-20s | %-15s%n", "Ma phieu nhap hang", "Ma san pham",
                "So luong nhap hang", "Don gia nhap hang", "Thanh tien");
        for (ImportDetail i : ls) {
            i.display();
        }
    }

    public int search(String importID) {
        for (int i = 0; i < n; i++) {
            if ((lsip[i].getImportID()).equals(importID)) {
                return i;
            }
        }
        return -1;
    }

    public Import searchIByDate(String date) {
        for (int i = 0; i < n; i++) {
            if ((lsip[i].getDate()).equals(date)) {
                return lsip[i];
            }
        }
        return null;
    }

    public Import searchIByEmployeeID(String employeeID) {
        for (int i = 0; i < n; i++) {
            if ((lsip[i].getEmployeeID()).equals(employeeID)) {
                return lsip[i];
            }
        }
        return null;
    }

    public Import searchIByProducerID(String producerID) {
        for (int i = 0; i < n; i++) {
            if ((lsip[i].getProducerID()).equals(producerID)) {
                return lsip[i];
            }
        }
        return null;
    }

    public Import searchIByTotalCost(int totalCost) {
        for (int i = 0; i < n; i++) {
            if ((lsip[i].getTotalCost()) == totalCost) {
                return lsip[i];
            }
        }
        return null;
    }

    public Import[] searchLsByImportID(String importID) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            if (lsip[i].getImportID().equals(importID)) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByEmployeeID(String employeeID) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            if (lsip[i].getEmployeeID().equals(employeeID)) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByProducerID(String producerID) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            if (lsip[i].getProducerID().equals(producerID)) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByDate(String date) {
        int count = 0;
        Import[] ls = new Import[n];
        if (Static.checkDate(date)) {
            for (int i = 0; i < n; i++) {
                if (lsip[i].getImportID().equals(date)) {
                    ls[count++] = lsip[i];
                }
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByTotalCost(int totalCost) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            if (lsip[i].getTotalCost() == totalCost) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByYear(int year) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            if (getYear(i) == year) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public int getYear(int i) {
        String subStr = lsip[i].getDate().substring(6, 6 + 4);
        int year = Integer.parseInt(subStr);
        return year;
    }

    public Import[] searchLsByOverTime(String dateBegin, String dateEnd) {
        int count = 0;
        Import[] ls = new Import[n];

        LocalDate date1 = LocalDate.parse(dateBegin, Static.dateFormat);
        LocalDate date2 = LocalDate.parse(dateEnd, Static.dateFormat);

        if (date1.isAfter(date2))
            return null;

        for (int i = 0; i < n; i++) {
            LocalDate date = LocalDate.parse(lsip[i].getDate(), Static.dateFormat);
            if (!date.isBefore(date1) && !date.isAfter(date2) || date.isEqual(date1) || date.isEqual(date2)) {
                ls[count++] = lsip[i];
            }
        }

        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Import[] searchLsByAmountOfTotalCost(int tt1, int tt2) {
        int count = 0;
        Import[] ls = new Import[n];
        for (int i = 0; i < n; i++) {
            int totalCost = lsip[i].getTotalCost();
            if (totalCost >= tt1 && totalCost <= tt2) {
                ls[count++] = lsip[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        String importID = inputImportID();
        remove(importID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma phieu nhap hang khong dung!");
            return;
        }

        LocalDate date1 = Static.strToLocalDate(lsip[index].getDate());
        LocalDate date2 = LocalDate.now();

        if (ChronoUnit.DAYS.between(date1, date2) > 7) {
            System.out.println("Khong the tra hang vi da qua thoi han quy dinh!");
            return;
        }

        int i;
        while ((i = lsimd.search(lsip[index].getImportID())) != -1) {
            lsimd.remove(i);
        }

        for (i = index; i < n - 1; i++) {
            lsip[i] = lsip[i + 1];
        }
        lsip = Arrays.copyOf(lsip, n - 1);
        n--;
        System.out.println("Tra hang thanh cong!");

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

        lsip[index].setDate();

        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String importID = inputImportID();
        edit(importID);
    }

    public void edit(String importID) {
        int index = search(importID);
        edit(index);
    }

    public void statistical() {
        int choice;
        do {
            Static.clearScreen();
            System.out.println("---- Thong ke ----");

            System.out.println("1. Tong quat");
            System.out.println("2. Theo nhan vien");
            System.out.println("3. Theo nha san xuat");
            System.out.println("4. Theo ngay nhap hang");
            System.out.println("5. Theo nam nhap hang");
            System.out.println("6. Theo khoang thoi gian nhap hang");
            System.out.println("7. Theo khoang tong tien nhap hang");
            System.out.println("8. Theo san pham");
            System.out.println("9. Thoat");
            System.out.print("Chon chuc nang (1-9): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    statisticalBasic();
                    break;
                case 2:
                    statisticalByEmployee();
                    break;
                case 3:
                    statisticalByCusstomer();
                    break;
                case 4:
                    statisticalByDate();
                    break;
                case 5:
                    statisticalByYear();
                    break;
                case 6:
                    statisticalByOverTime();
                    break;
                case 7:
                    statisticalByAmountOfMoney();
                    break;
                case 8:
                    statisticalByProduct();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 9);
    }

    private void statisticalBasic() {
        Static.clearScreen();

        System.out.println("---- Thong ke tong quat ----");

        int total = 0;
        System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", "Ma phieu nhap", "Ma nhan vien",
                "Ma nha san xuat", "Ngay nhap hang", "Tong tien/don nhap hang");
        for (Import i : lsip) {
            i.display();
            total += i.getTotalCost();
        }
        System.out.println("Tong tien: " + total + ", So luong don nhap hang: " + n);

        Static.exit();
    }

    private void statisticalByCusstomer() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo nha san xuat ----");

        Set<String> producer = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Nha san display", "So tien da chi", "So luong don nhap hang");
        for (int i = 0; i < n; i++) {
            String producerID = lsip[i].getProducerID();

            // Nếu producerID đã được xử lý, bỏ qua
            if (producer.contains(producerID)) {
                continue;
            }

            int count = 0;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (lsip[j].getProducerID().equals(producerID)) {
                    count++;
                    sum += lsip[j].getTotalCost();
                }
            }
            System.out.println("-----------------+----------------------+------------------------");
            System.out.format(" %-15s | %-20s | %-20s%n", producerID, sum, count);

            // Đánh dấu producerID đã được xử lý
            producer.add(producerID);
            total += sum;
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }

    private void statisticalByEmployee() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo nhan vien ----");

        Set<String> NhanVien = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Nhan vien", "So tien da chi", "So luong don nhap hang");
        for (int i = 0; i < n; i++) {
            String employeeID = lsip[i].getEmployeeID();

            if (NhanVien.contains(employeeID)) {
                continue;
            }

            int count = 0;
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if (lsip[j].getEmployeeID().equals(employeeID)) {
                    count++;
                    sum += lsip[j].getTotalCost();
                }
            }
            System.out.println("-----------------+----------------------+------------------------");
            System.out.format(" %-15s | %-20s | %-20s%n", employeeID, sum, count);

            NhanVien.add(employeeID);
            total += sum;
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }

    private void statisticalByDate() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo ngay nhap hang ----");

        Set<String> Dates = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Ngay", "So tien da chi", "So luong don nhap hang");
        for (int i = 0; i < n; i++) {
            String date = lsip[i].getDate();

            if (Dates.contains(date)) {
                continue;
            }

            int count = 0;
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if (lsip[j].getDate().equals(date)) {
                    count++;
                    sum += lsip[j].getTotalCost();
                }
            }
            System.out.println("-----------------+----------------------+------------------------");
            System.out.format(" %-15s | %-20s | %-20s%n", date, sum, count);

            Dates.add(date);
            total += sum;
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }

    private void statisticalByYear() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo nam nhap hang ----");

        Set<Integer> YearSet = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Nam", "So tien da chi", "So luong don nhap hang");
        for (int i = 0; i < n; i++) {
            int year = getYear(i);

            if (YearSet.contains(year)) {
                continue;
            }

            int sum = 0;
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (getYear(j) == year) {
                    sum += lsip[j].getTotalCost();
                    count++;
                }
            }

            System.out.println("-----------------+----------------------+------------------------");
            System.out.format(" %-15s | %-20s | %-20s%n", year, sum, count);

            YearSet.add(year);

            total += sum;
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }

    private void statisticalByOverTime() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo khoang thoi gian nhap hang ----");

        System.out.print("Nhap ngay bat dau: ");
        String dateBegin = Static.scanner.nextLine();
        System.out.print("Nhap ngay ket thuc: ");
        String dateEnd = Static.scanner.nextLine();

        Import[] ls = searchLsByOverTime(dateBegin, dateEnd);
        if (ls == null) {
            System.out.println("Khong co don nhap hang trong khoang thoi gian nay.");
        } else {
            int total = 0;
            int count = 0;
            System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", "Ma phieu nhap", "Ma nhan vien",
                    "Ma nha san xuat", "Ngay nhap hang", "Tong tien/don nhap hang");
            for (Import i : ls) {
                i.display();
                total += i.getTotalCost();
                count++;
            }
            System.out.println("Tong tien: " + total + ", So luong don nhap hang: " + count);
        }

        Static.exit();
    }

    private void statisticalByAmountOfMoney() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo tong tien nhap hang ----");

        System.out.print("Nhap tong tien toi thieu: ");
        int min = Static.checkInputIsInt();
        System.out.print("Nhap tong tien toi da: ");
        int max = Static.checkInputIsInt();
        Static.scanner.nextLine();

        Import[] ls = searchLsByAmountOfTotalCost(min, max);
        if (ls == null) {
            System.out.println("Khong co don nhap hang nao trong khoang tong tien nay.");
        } else {
            int count = 0;
            int total = 0;
            System.out.format(" %-20s | %-20s | %-20s | %-20s | %-20s%n", "Ma phieu nhap", "Ma nhan vien",
                    "Ma nha san display", "Ngay nhap hang", "Tong tien/don nhap hang");
            for (Import i : ls) {
                i.display();
                total += i.getTotalCost();
                count++;
            }
            System.out.println("Tong tien: " + total + ", So luong don nhap hang: " + count);
        }

        Static.exit();
    }

    private int totalByProductID(String productID) {
        int total = 0;
        ImportDetail[] ls = lsimd.searchLsByProductID(productID);
        for (ImportDetail id : ls) {
            total += id.getCost();
        }
        return total;
    }

    private void statisticalByProduct() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo san pham ----");

        int total = 0;
        System.out.format(" %-15s | %-25s| %-20s%n", "Ma san pham", "Ten san pham", "Tong tien mua vao");
        for (int i = 0; i < lspd.length(); i++) {
            String productID = lspd.getProduct(i).getProductID();
            System.out.println("-----------------+--------------------------+------------------------");
            System.out.format(" %-15s | %-25s| %-20s%n", productID, lspd.getProductNameByProdcutID(productID),
                    totalByProductID(productID));
            total += totalByProductID(productID);
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }
}