package management;

import control.Static;
import list.*;

public class ManagementBill {
    private ListBill lsb;
    private String customerID;

    public ManagementBill() {
    }

    public ManagementBill(ListBill lsb) {
        this.lsb = lsb;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void admin() {
        lsb.setCustomerID(null);
        int choice = 0;
        do {
            Static.clearScreen();

            System.out.println("---- Hoa don ----");
            System.out.println("1. Hien thi danh sach hoa don");
            System.out.println("2. Them hoa don");
            System.out.println("3. Sua thong tin hoa don");
            System.out.println("4. Xoa hoa don");
            System.out.println("5. Tra cuu hoa don");
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsb.display();
                    break;
                case 2:
                    lsb.add();
                    break;
                case 3:
                    lsb.edit();
                    break;
                case 4:
                    lsb.remove();
                    break;
                case 5:
                    lsb.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }

    public void user() {
        lsb.setCustomerID(customerID);
        int choice = 0;

        do {
            Static.clearScreen();

            System.out.println("1. Mua hang");
            System.out.println("2. Lich su mua hang");
            System.out.println("3. Tra cuu hoa don");
            System.out.println("4. Tra hang");
            System.out.println("5.Thoat");
            System.out.print("Chon chuc nang (1-5): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsb.add();
                    break;
                case 2:
                    lsb.displayLsByCustomerID();
                    break;
                case 3:
                    lsb.search();
                    break;
                case 4:
                    lsb.remove();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 5);
    }
}
