package management;

import control.Static;
import list.*;

public class ManagementCustomer {
    private ListCustomer lsctm;

    public ManagementCustomer() {
    }

    public ManagementCustomer(ListCustomer lsctm) {
        this.lsctm = lsctm;
    }

    public void menu() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Khach hang ----");
            System.out.println("1. Hien thi danh sach khach hang");
            System.out.println("2. Them khach hang");
            System.out.println("3. Xoa thong tin khach hang");
            System.out.println("4. Sua khach hang");
            System.out.println("5. Tim kiem khach hang");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsctm.display();
                    break;
                case 2:
                    lsctm.add();
                    break;
                case 3:
                    lsctm.remove();
                    break;
                case 4:
                    lsctm.edit();
                    break;
                case 5:
                    lsctm.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
