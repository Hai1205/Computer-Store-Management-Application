package management;

import control.Static;
import list.*;

public class ManagementEmployee {
    private ListEmployee lsem;

    public ManagementEmployee() {
    }

    public ManagementEmployee(ListEmployee lsem) {
        this.lsem = lsem;
    }

    public void menu() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Nhan vien ----");
            System.out.println("1. Hien thi danh sach nhan vien");
            System.out.println("2. Them nhan vien");
            System.out.println("3. Xoa thong tin nhan vien");
            System.out.println("4. Sua nhan vien");
            System.out.println("5. Tim kiem nhan vien");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsem.display();
                    break;
                case 2:
                    lsem.add();
                    break;
                case 3:
                    lsem.remove();
                    break;
                case 4:
                    lsem.edit();
                    break;
                case 5:
                    lsem.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
