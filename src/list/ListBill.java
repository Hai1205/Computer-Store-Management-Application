package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import control.*;
import element.*;

public class ListBill implements InterfaceList<Bill> {
    private Bill[] lsb;
    private int n;

    private ListProduct lspd;
    private ListBillDetail lsbd;
    private ListEmployee lsem;
    private ListCustomer lsctm;
    private ListWarranty lswrt;

    private String customerID;

    public ListBill() {
        n = 0;
        lsb = new Bill[n];
        readFromFile();
    }

    public ListBill(ListProduct lspd, ListBillDetail lsbd, ListEmployee lsem, ListCustomer lsctm,
            ListWarranty lswrt) {
        n = 0;
        lsb = new Bill[n];
        this.lspd = lspd;
        this.lsbd = lsbd;
        this.lsem = lsem;
        this.lsctm = lsctm;
        this.lswrt = lswrt;
        readFromFile();
    }

    public ListBill(ListBill other) {
        this.n = other.n;
        this.lsb = Arrays.copyOf(other.lsb, n);
        readFromFile();
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int length() {
        return n;
    }

    public Bill getBill(int i) {
        return lsb[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so luong hoa don: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }

        Static.exit();
    }

    public void display() {
        Static.clearScreen();

        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                "Ngay mua", "Tong tien/hoa don");
        for (Bill i : lsb) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListBill.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Bill i : lsb) {
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
            FileReader fr = new FileReader("../src/data_base/ListBill.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 5) {
                    String billID = txt[0].trim();
                    String employeeID = txt[1].trim();
                    String customerID = txt[2].trim();
                    String date = txt[3].trim();
                    int totalCost = Integer.parseInt(txt[4].trim());
                    add(new Bill(billID, employeeID, customerID, date, totalCost));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createBillID() {
        String billID;
        do {
            billID = "BL" + Static.randomID();
        } while (search(billID) != -1);

        return billID;
    }

    public String inputBillID() {
        String input;
        while (true) {
            System.out.print("Nhap ma hoa don: ");
            input = Static.inputStringNoSpace();
            if (search(input) != -1) {
                break;
            }
            System.out.println("Ma hoa don khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        lsb = Arrays.copyOf(lsb, n + 1);

        String billID = createBillID();

        String employeeID = "";

        if (customerID == null || customerID.isEmpty()) {
            employeeID = lsem.inputEmployeeID();
            customerID = lsctm.inputCustomerID();
        } else {
            employeeID = lsem.autoSelectemployeeID();
        }

        lsb[n] = new Bill(billID, employeeID, customerID, null, 0);
        lsb[n].setlsod(lsbd);
        lsb[n].input();

        lsbd.setListProduct(lspd);
        lsbd.setBillID(billID);
        lsbd.setCustomerID(customerID);
        lsbd.input();

        lsem.increaseKPI(employeeID);

        lsb[n++].setTotalCost();

        System.out.println("Mua hang thanh cong!");
        System.out.println("Quy khach co the doi tra san pham trong vong 7 ngay, tinh tu luc mua hang!");
        search(n - 1);

        writeToFile(false);

        Static.exit();
    }

    public void add(Bill x) {
        lsb = Arrays.copyOf(lsb, n + 1);
        lsb[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String billID = inputBillID();
        int index = search(billID);
        if (index == -1 || !lsb[index].getCustomerID().equals(customerID)) {
            System.out.println("Hoa don khong ton tai hoac khong thuoc ve tai khoan nay!");
            Static.exit();
            return;
        }

        search(index);

        Static.exit();
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
            return;
        }

        System.out.println("- Hoa don:");
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                "Ngay mua", "Tong tien/hoa don");
        lsb[index].display();

        System.out.println("+ Chi tiet hoa don: ");
        BillDetail[] ls = lsbd.searchLsByBillID(lsb[index].getBillID());
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-10s | %-15s%n", "Ma hoa don", "Ma san pham",
                "Ma bao hanh", "So luong mua", "Don gia", "Thanh tien");
        for (BillDetail i : ls) {
            i.display();
        }
    }

    public int search(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lsb[i].getBillID()).equals(billID)) {
                return i;
            }
        }
        return -1;
    }

    public Bill searchOByBillID(String billID) {
        for (int i = 0; i < n; i++) {
            if ((lsb[i].getBillID()).equals(billID)) {
                return lsb[i];
            }
        }
        return null;
    }

    public Bill searchOByEmployeeID(String employeeID) {
        for (int i = 0; i < n; i++) {
            if ((lsb[i].getEmployeeID()).equals(employeeID)) {
                return lsb[i];
            }
        }
        return null;
    }

    public Bill searchOByCustomerID(String customerID) {
        for (int i = 0; i < n; i++) {
            if ((lsb[i].getCustomerID()).equals(customerID)) {
                return lsb[i];
            }
        }
        return null;
    }

    public Bill searchOByDate(String date) {
        for (int i = 0; i < n; i++) {
            if (lsb[i].getDate().equals(date)) {
                return lsb[i];
            }
        }
        return null;
    }

    public Bill searchOByTotalCost(int totalCost) {
        for (int i = 0; i < n; i++) {
            if ((lsb[i].getTotalCost()) == totalCost) {
                return lsb[i];
            }
        }
        return null;
    }

    public Bill[] searchLsByTotalCost(int totalCost) {
        int count = 0;
        Bill[] ls = new Bill[n];
        for (int i = 0; i < n; i++) {
            if (lsb[i].getTotalCost() == totalCost) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Bill[] searchLsByBillID(String billID) {
        int count = 0;
        Bill[] ls = new Bill[n];
        for (int i = 0; i < n; i++) {
            if (lsb[i].getBillID().equals(billID)) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Bill[] searchLsByEmployeeID(String employeeID) {
        int count = 0;
        Bill[] ls = new Bill[n];
        for (int i = 0; i < n; i++) {
            if (lsb[i].getEmployeeID().equals(employeeID)) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void displayLsByCustomerID() {
        Static.clearScreen();

        Bill[] ls = searchLsByCustomerID(customerID);
        if (ls != null) {
            System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                    "Ngay mua", "Tong tien/hoa don");
            for (Bill i : ls) {
                i.display();
            }
        } else {
            System.out.println("Lich su mua hang cua khach hang hien dang trong!");
            System.out.println("Hay bat dau mua hang nao!");
        }

        Static.exit();
    }

    public Bill[] searchLsByCustomerID(String customerID) {
        int count = 0;
        Bill[] ls = new Bill[n];
        for (int i = 0; i < n; i++) {
            if (lsb[i].getCustomerID().equals(customerID)) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Bill[] searchLsByDate(String date) {
        int count = 0;
        Bill[] ls = new Bill[n];

        for (int i = 0; i < n; i++) {
            if (lsb[i].getDate().equals(date)) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public int getYear(int i) {
        String subStr = lsb[i].getDate().substring(6, 6 + 4);
        int Year = Integer.parseInt(subStr);
        return Year;
    }

    public Bill[] searchLsByOverTime(String dateBegin, String dateEnd) {
        int count = 0;
        Bill[] ls = new Bill[n];

        LocalDate date1 = LocalDate.parse(dateBegin, Static.dateFormat);
        LocalDate date2 = LocalDate.parse(dateEnd, Static.dateFormat);

        if (date1.isAfter(date2))
            return null;

        for (int i = 0; i < n; i++) {
            LocalDate date = LocalDate.parse(lsb[i].getDate(), Static.dateFormat);
            if (!date.isBefore(date1) && !date.isAfter(date2)) {
                ls[count++] = lsb[i];
            }
        }

        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Bill[] searchLsByAmountOfTotalCost(int total1, int total2) {
        int count = 0;
        Bill[] ls = new Bill[n];
        for (int i = 0; i < n; i++) {
            int totalCost = lsb[i].getTotalCost();
            if (totalCost >= total1 && totalCost <= total2) {
                ls[count++] = lsb[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        String billID = inputBillID();
        remove(billID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
            return;
        }

        LocalDate date1 = Static.strToLocalDate(lsb[index].getDate());
        LocalDate date2 = LocalDate.now();

        if (ChronoUnit.DAYS.between(date1, date2) > 7) {
            System.out.println("Khong the tra hang vi da qua thoi han quy dinh!");
            return;
        }

        int i;
        while ((i = lsbd.search(lsb[index].getBillID())) != -1) {
            lsbd.remove(i);
        }

        lsem.reduceKPI(lsb[index].getEmployeeID());

        for (i = index; i < n - 1; i++) {
            lsb[i] = lsb[i + 1];
        }
        lsb = Arrays.copyOf(lsb, n - 1);
        n--;
        System.out.println("Tra hang thanh cong!");

        writeToFile(false);
    }

    public void remove(String billID) {
        int index = search(billID);
        remove(index);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma hoa don khong dung!");
            return;
        }

        lsb[index].setDate();

        for (int i = 0; i < lswrt.length(); i++) {
            if ((lswrt.getWarranty(i).getBillID()).equals(lsb[index].getBillID())) {
                lswrt.getWarranty(i).setDatePurchase(lsb[index].getDate());
            }
        }
        lswrt.writeToFile(false);

        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String billID = inputBillID();
        edit(billID);
    }

    public void edit(String billID) {
        int index = search(billID);
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
            System.out.println("4. Theo ngay ban hang");
            System.out.println("5. Theo nam ban hang");
            System.out.println("6. Theo khoang thoi gian ban hang");
            System.out.println("7. Theo khoang tong tien ban hang");
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
        System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                "Ngay mua", "Tong tien/hoa don");
        for (Bill i : lsb) {
            i.display();
            total += i.getTotalCost();
        }
        System.out.println("Tong tien: " + total + ", So luong hoa don: " + n);

        Static.exit();
    }

    private void statisticalByCusstomer() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo khach hang ----");

        Set<String> customer = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Ma khach hang", "So tien da thu", "So luong hoa don");
        for (int i = 0; i < n; i++) {
            String customerID = lsb[i].getCustomerID();

            // Nếu customerID đã được xử lý, bỏ qua
            if (customer.contains(customerID)) {
                continue;
            }

            int count = 0;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                // Tìm các đơn nhập hàng có cùng customerID
                if (lsb[j].getCustomerID().equals(customerID)) {
                    count++;
                    sum += lsb[j].getTotalCost();
                }
            }
            System.out.println("-----------------+----------------------+------------------------");
            System.out.format(" %-15s | %-20s | %-20s%n", customerID, sum, count);

            // Đánh dấu customerID đã được xử lý
            customer.add(customerID);

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

        System.out.format(" %-15s | %-20s | %-20s%n", "Ma nhan vien", "So tien da thu", "So luong hoa don");
        for (int i = 0; i < n; i++) {
            String employeeID = lsb[i].getEmployeeID();

            if (NhanVien.contains(employeeID)) {
                continue;
            }

            int count = 0;
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (lsb[j].getEmployeeID().equals(employeeID)) {
                    count++;
                    sum += lsb[j].getTotalCost();
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

        System.out.println("---- Thong ke theo ngay ban hang ----");

        Set<String> Dates = new HashSet<>();

        int total = 0;

        System.out.format(" %-15s | %-20s | %-20s%n", "Ngay", "So tien da thu", "So luong hoa don");
        for (int i = 0; i < n; i++) {
            String date = lsb[i].getDate();

            if (Dates.contains(date)) {
                continue;
            }

            int count = 0;
            int sum = 0;

            for (int j = 0; j < n; j++) {
                if (lsb[j].getDate().equals(date)) {
                    count++;
                    sum += lsb[j].getTotalCost();
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

        System.out.println("---- Thong ke theo nam ban hang ----");

        Set<Integer> YearSet = new HashSet<>();

        int total = 0;

        System.out.format(" %-10s | %-20s | %-20s%n", "Nam", "So tien da thu", "So luong hoa don");
        for (int i = 0; i < n; i++) {
            int Year = getYear(i);

            if (YearSet.contains(Year)) {
                continue;
            }

            int sum = 0;
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (getYear(j) == Year) {
                    sum += lsb[j].getTotalCost();
                    count++;
                }
            }

            System.out.println("------------+----------------------+------------------------");
            System.out.format(" %-10s | %-20s | %-20s%n", Year, sum, count);

            YearSet.add(Year);

            total += sum;
        }
        System.out.println("Tong tien: " + total);

        Static.exit();
    }

    private void statisticalByOverTime() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo khoang thoi gian ban hang ----");

        System.out.print("Nhap ngay bat dau: ");
        String dateBegin = Static.inputDate();
        System.out.print("Nhap ngay ket thuc: ");
        String dateEnd = Static.inputDate();

        Bill[] ls = searchLsByOverTime(dateBegin, dateEnd);
        if (ls == null) {
            System.out.println("Khong co hoa don trong khoang thoi gian nay.");
        } else {
            int total = 0;
            int count = 0;
            System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                    "Ngay mua", "Tong tien/hoa don");
            for (Bill b : ls) {
                b.display();
                total += b.getTotalCost();
                count++;
            }
            System.out.println("Tong tien: " + total + ", So luong hoa don: " + count);
        }

        Static.exit();
    }

    private void statisticalByAmountOfMoney() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo tong tien ban hang ----");

        System.out.print("Nhap tong tien toi thieu: ");
        int min = Static.checkInputIsInt();
        System.out.print("Nhap tong tien toi da: ");
        int max = Static.checkInputIsInt();
        Static.scanner.nextLine();

        Bill[] ls = searchLsByAmountOfTotalCost(min, max);
        if (ls == null) {
            System.out.println("Khong co hoa don nao trong khoang tong tien nay.");
        } else {
            int count = 0;
            int total = 0;
            System.out.format(" %-15s | %-15s | %-15s | %-15s | %-20s%n", "Ma hoa don", "Ma nhan vien", "Ma khach hang",
                    "Ngay mua", "Tong tien/hoa don");
            for (Bill b : ls) {
                b.display();
                total += b.getTotalCost();
                count++;
            }
            System.out.println("Tong tien: " + total + ", So luong hoa don: " + count);
        }

        Static.exit();
    }

    private int totalByProductID(String productID) {
        int total = 0;
        BillDetail[] ls = lsbd.searchLsByProductID(productID);
        for (BillDetail b : ls) {
            total += b.getCost();
        }
        return total;
    }

    private void statisticalByProduct() {
        Static.clearScreen();

        System.out.println("---- Thong ke theo san pham ----");

        int total = 0;
        System.out.format(" %-15s | %-25s| %-20s%n", "Ma san pham", "Ten san pham", "Tong tien ban ra");
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