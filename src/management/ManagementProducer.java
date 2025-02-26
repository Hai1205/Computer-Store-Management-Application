package management;

import control.Static;
import list.*;

public class ManagementProducer {
    private ListProducer lspdc;

    public ManagementProducer() {
    }

    public ManagementProducer(ListProducer lspdc) {
        this.lspdc = lspdc;
    }

    public void menu() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- Nha san xuat ----");
            System.out.println("1. Hien thi danh sach nha san xuat");
            System.out.println("2. Them nha san xuat");
            System.out.println("3. Xoa thong tin nha san xuat");
            System.out.println("4. Sua nha san xuat");
            System.out.println("5. Tim kiem nha san xuat");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspdc.display();
                    break;
                case 2:
                    lspdc.add();
                    break;
                case 3:
                    lspdc.remove();
                    break;
                case 4:
                    lspdc.edit();
                    break;
                case 5:
                    lspdc.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
