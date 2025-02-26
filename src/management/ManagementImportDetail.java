package management;

import control.Static;
import list.*;

public class ManagementImportDetail {
    private ListImportDetail lsipd;

    public ManagementImportDetail() {
    }

    public ManagementImportDetail(ListImportDetail lsipd) {
        this.lsipd = lsipd;
    }

    public void menu() {
        Static.clearScreen();

        int choice;
        do {
            System.out.println("---- Chi tiet phieu nhap hang ----");
            System.out.println("1. Hien thi danh sach chi tiet phieu nhap hang");
            System.out.println("2. Them chi tiet phieu nhap hang");
            System.out.println("3. Xoa thong tin chi tiet phieu nhap hang");
            System.out.println("4. Sua chi tiet phieu nhap hang");
            System.out.println("5. Tim kiem chi tiet phieu nhap hang");
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsipd.display();
                    break;
                case 2:
                    lsipd.add();
                    break;
                case 3:
                    lsipd.remove();
                    break;
                case 4:
                    lsipd.edit();
                    break;
                case 5:
                    lsipd.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
