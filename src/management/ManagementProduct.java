package management;

import control.Static;
import list.*;

public class ManagementProduct {
    private ListProduct lspd;

    public ManagementProduct() {
    }

    public ManagementProduct(ListProduct lspd) {
        this.lspd = lspd;
    }

    public void admin() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- San pham ----");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Xoa san pham");
            System.out.println("3. Sua thong tin san pham");
            System.out.println("4. Tra cuu thong tin san pham");
            System.out.println("5. Tim kiem san pham theo gia");
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspd.display();
                    break;
                case 2:
                    lspd.remove();
                    break;
                case 3:
                    lspd.edit();
                    break;
                case 4:
                    lspd.search();
                    break;
                case 5:
                    lspd.searchLsByAmountOfPrice();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }

    public void user() {
        int choice;
        do {
            Static.clearScreen();

            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Tra cuu thong tin san pham");
            System.out.println("3. Tim kiem san pham theo gia");
            System.out.println("4. Thoat");
            System.out.print("Chon chuc nang (1-4): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspd.display();
                    break;
                case 2:
                    lspd.search();
                    break;
                case 3:
                    lspd.searchLsByAmountOfPrice();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 4);
    }
}