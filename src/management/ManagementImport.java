package management;

import control.Static;
import list.*;

public class ManagementImport {
    private ListImport lsip;

    public ManagementImport() {
    }

    public ManagementImport(ListImport lsip) {
        this.lsip = lsip;
    }

    public void menu() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Phieu nhap hang ----");
            System.out.println("1. Hien thi danh sach phieu nhap hang");
            System.out.println("2. Nhap hang");
            System.out.println("3. Sua phieu nhap hang");
            System.out.println("4. Xoa phieu nhap hang");
            System.out.println("5. Tra cuu phieu nhap hang");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsip.display();
                    break;
                case 2:
                    lsip.add();
                    break;
                case 3:
                    lsip.edit();
                    break;
                case 4:
                    lsip.remove();
                    break;
                case 5:
                    lsip.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
