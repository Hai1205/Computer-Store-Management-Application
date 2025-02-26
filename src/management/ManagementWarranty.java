package management;

import control.Static;
import list.*;

public class ManagementWarranty {
    private ListWarranty lswrt;
    private String customerID;

    public ManagementWarranty() {
    }

    public ManagementWarranty(ListWarranty lswrt) {
        this.lswrt = lswrt;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void admin() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Phieu bao hanh ----");
            System.out.println("1. Hien thi danh sach phieu bao hanh");
            System.out.println("2. Them phieu bao hanh");
            System.out.println("3. Xoa phieu bao hanh");
            System.out.println("4. Sua thong tin phieu bao hanh");
            System.out.println("5. Tim kiem phieu bao hanh");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lswrt.display();
                    break;
                case 2:
                    lswrt.add();
                    break;
                case 3:
                    lswrt.remove();
                    break;
                case 4:
                    lswrt.edit();
                    break;
                case 5:
                    lswrt.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }

    public void user() {
        lswrt.setCustomerID(customerID);
        int choice = 0;

        do {
            Static.clearScreen();

            System.out.println("1. Danh sach tat ca phieu bao hanh");
            System.out.println("2. Danh sach phieu bao hanh chua het han");
            System.out.println("3. Danh sach phieu bao hanh da het han");
            System.out.println("4. Tra cuu phieu bao hanh");
            System.out.println("5. Thoat");
            System.out.print("Chon chuc nang (1-5): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lswrt.lsUser();
                    break;
                case 2:
                    lswrt.lsDateRemain();
                    ;
                    break;
                case 3:
                    lswrt.lsDateEnd();
                    ;
                    break;
                case 4:
                    lswrt.search();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 5);
    }
}
