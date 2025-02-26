package management;

import control.Static;
import list.*;

public class ManagementProductDetail {
    private ListProductDetail lspdd;

    public ManagementProductDetail() {
    }

    public ManagementProductDetail(ListProductDetail lspdd) {
        this.lspdd = lspdd;
    }

    public void menu() {
        Static.clearScreen();

        int choice;
        do {
            System.out.println("---- Chi tiet san pham ----");
            System.out.println("1. Hien thi danh sach chi tiet san pham");
            System.out.println("2. Them chi tiet san pham");
            System.out.println("3. Xoa thong tin chi tiet san pham");
            System.out.println("4. Sua chi tiet san pham");
            System.out.println("5. Tim kiem chi tiet san pham");
            System.out.println("6. Thoat");
            System.out.print("chon chuc nang (1-6): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspdd.display();
                    break;
                case 2:
                    lspdd.add();
                    break;
                case 3:
                    lspdd.remove();
                    break;
                case 4:
                    lspdd.edit();
                    break;
                case 5:
                    lspdd.search();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Lua chon khong hop le! Vui long chon lai!");
            }
        } while (choice != 6);
    }
}
