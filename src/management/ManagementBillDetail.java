package management;

import control.Static;
import list.*;

public class ManagementBillDetail {
    private ListBillDetail lsbd;

    public ManagementBillDetail() {
    }

    public ManagementBillDetail(ListBillDetail lsbd) {
        this.lsbd = lsbd;
    }

    public void menu() {
        Static.clearScreen();

        int choice;
        do {
            System.out.println("---- Chi tiet hoa don ----");
            System.out.println("1. Hien thi danh sach chi tiet hoa don");
            System.out.println("2. Them chi tiet hoa don");
            System.out.println("3. Xoa chi tiet hoa don");
            System.out.println("4. Sua thong tin chi tiet hoa don");
            System.out.println("5. Tim kiem chi tiet hoa don");
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsbd.display();
                    break;
                case 2:
                    lsbd.add();
                    break;
                case 3:
                    lsbd.remove();
                    break;
                case 4:
                    lsbd.edit();
                    break;
                case 5:
                    lsbd.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
