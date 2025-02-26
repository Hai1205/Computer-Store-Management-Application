package control;

import java.io.*;

import list.*;
import management.*;

public class Account {
    private ListCustomer lsctm;
    private ListBill lsod;
    private ListImport lsip;

    private ManagementBill mod;
    private ManagementImport mip;
    private ManagementWarranty mwrt;
    private ManagementEmployee mem;
    private ManagementProducer mpdc;
    private ManagementProduct mpd;
    private ManagementCustomer mctm;

    private String username, password, customerID;

    public Account() {
        ListProductDetail lspdd = new ListProductDetail();
        ListWarranty lswrt = new ListWarranty();
        ListEmployee lsem = new ListEmployee();
        ListProducer lspdc = new ListProducer();
        lsctm = new ListCustomer();

        ListProduct lspd = new ListProduct(lspdd);
        ListBillDetail lsodd = new ListBillDetail(lspd, lswrt);
        ListImportDetail lsipd = new ListImportDetail(lspd);
        lsod = new ListBill(lspd, lsodd, lsem, lsctm, lswrt);
        lsip = new ListImport(lspd, lsipd, lspdc, lsem);

        mpd = new ManagementProduct(lspd);
        mod = new ManagementBill(lsod);
        mip = new ManagementImport(lsip);
        mwrt = new ManagementWarranty(lswrt);
        mem = new ManagementEmployee(lsem);
        mpdc = new ManagementProducer(lspdc);
        mctm = new ManagementCustomer(lsctm);
    }

    public void menu() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Menu ----");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("3. Thoat");
            System.out.print("Chon chuc nang (1-3): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("Chuong trinh ket thuc!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
                    Static.exit();
            }
        } while (choice != 3);
    }

    private void signIn() {
        Static.clearScreen();

        String Username, Password;

        System.out.println("---- Dang nhap ----");
        while (true) {
            System.out.print("Nhap ten dang nhap: ");
            Username = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (checkUsername(Username)) {
                break;
            }
            System.out.println("Ten dang nhap khong dung! Xin nhap lai!");
        }

        while (true) {
            System.out.print("Nhap mat khau: ");
            Password = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (Password.equals(password)) {
                break;
            }
            System.out.println("Mat khau khong dung! Xin nhap lai!");
        }

        if (customerID.equals("null")) {
            admin();
        } else {
            user();
        }
    }

    private void signUp() {
        Static.clearScreen();

        System.out.println("---- Dang ky ----");
        do {
            System.out.print("Nhap ten dang nhap: ");
            username = Static.scanner.nextLine();

            if (username.isEmpty()) {
                System.out.println("Ten dang nhap khong duoc de trong! Xin nhap lai!");
            } else if (Static.checkSpace(username)) {
                System.out.println("Ten dang nhap khong duoc co khoang trang! Xin nhap lai!");
            } else if (checkUsername(username)) {
                System.out.println("Ten dang nhap da duoc su dung! Xin nhap lai!");
            }
        } while (username.isEmpty() || Static.checkSpace(username) || checkUsername(username));

        String rePassword;
        do {
            System.out.print("Nhap mat khau: ");
            setPassword(Static.scanner.nextLine());

            if (password.isEmpty()) {
                System.out.println("Mat khau khong duoc de trong! Xin nhap lai!");
            } else if (Static.checkSpace(password)) {
                System.out.println("Mat khau khong duoc co khoang trang! Xin nhap lai!");
            }
        } while (password.isEmpty() || Static.checkSpace(password));

        do {
            System.out.print("Nhap lai mat khau: ");
            rePassword = Static.scanner.nextLine();

            if (rePassword.isEmpty()) {
                System.out.println("Vui long nhap lai mat khau!");
            } else if (Static.checkSpace(rePassword)) {
                System.out.println("Mat khau khong duoc co khoang trang! Xin nhap lai!");
            } else if (!password.equals(rePassword)) {
                System.out.println("Mat khau nhap lai khong khop! Xin nhap lai!");
            }
        } while (rePassword.isEmpty() || Static.checkSpace(rePassword) || !password.equals(rePassword));

        customerID = lsctm.createCustomerID();
        lsctm.setCustomerID(customerID);
        lsctm.add();

        System.out.println("Dang ky thanh cong!");

        writeToFile(true);
        Static.exit();
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void management() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Quan ly -----");
            System.out.println("1. San pham");
            System.out.println("2. Nhap hang");
            System.out.println("3. Nhan vien");
            System.out.println("4. Khach hang");
            System.out.println("5. Nha san xuat");
            System.out.println("6. Bao hanh");
            System.out.println("7. Hoa don");
            System.out.println("8. Thoat");
            System.out.print("chon chuc nang (1-8): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    mpd.admin();
                    break;
                case 2:
                    mip.menu();
                    break;
                case 3:
                    mem.menu();
                    break;
                case 4:
                    mctm.menu();
                    break;
                case 5:
                    mpdc.menu();
                    break;
                case 6:
                    mwrt.admin();
                    break;
                case 7:
                    mod.admin();
                case 8:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 8);
    }

    private void statistical() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Thong ke ----");
            System.out.println("1. Thong ke hoa don");
            System.out.println("2. Thong ke nhap hang");
            System.out.println("3. Thoat");
            System.out.print("Chon chuc nang (1-3): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsod.statistical();
                    break;
                case 2:
                    lsip.statistical();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 3);
    }

    private void admin() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- " + username + " ----");
            System.out.println("1. Quan ly cua hang");
            System.out.println("2. Thong ke");
            System.out.println("3. Dang xuat");
            System.out.print("Chon chuc nang (1-3): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    management();
                    break;
                case 2:
                    statistical();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 3);
    }

    private void setting() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Cai dat ----");
            System.out.println("1. Thong tin");
            System.out.println("2. Sua thong tin");
            System.out.println("3. Thoat");
            System.out.print("Chon chuc nang (1-3): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    int index = lsctm.search(customerID);
                    System.out.format(" %-15s | %-20s | %-15s | %-15s | %-15s%n", "Ma khach hang", "Ho khach hang",
                            "Ten khach hang", "Dia chi", "So dien thoai");
                    lsctm.getCustomer(index).display();
                    Static.exit();
                    break;
                case 2:
                    lsctm.edit(customerID);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 3);
    }

    private void user() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- " + username + " ----");
            System.out.println("1. San pham");
            System.out.println("2. Gio hang");
            System.out.println("3. Phieu bao hanh");
            System.out.println("4. Cai dat");
            System.out.println("5. Dang xuat");
            System.out.print("Chon chuc nang (1-5): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    mpd.user();
                    break;
                case 2:
                    mod.setCustomerID(customerID);
                    mod.user();
                    break;
                case 3:
                    mwrt.setCustomerID(customerID);
                    mwrt.user();
                    break;
                case 4:
                    setting();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 5);
    }

    private boolean checkUsername(String username) {
        try {
            FileReader fr = new FileReader("../src/data_base/ListAccount.txt");
            try (BufferedReader br = new BufferedReader(fr)) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] txt = line.split(", ");
                    if (txt.length == 3 && txt[0].equals(username)) {
                        setUsername(username);
                        setPassword(txt[1]);
                        setCustomerID(txt[2]);
                        return true;
                    }
                }
                br.close();
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fr = new FileWriter("../src/data_base/ListAccount.txt", dontDeleteData);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(username + ", " + password + ", " + customerID);
            br.newLine();

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
