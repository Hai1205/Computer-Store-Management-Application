package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import control.*;
import element.*;

public class ListProduct implements InterfaceList<Product> {
    private Product[] lspd;
    private int n;

    private ListProductDetail lspdd;
    private String productID, producerID;
    private int quantity, price;

    public ListProduct() {
        n = 0;
        lspd = new Product[n];
        readFromFile();
    }

    public ListProduct(ListProductDetail lspdd) {
        n = 0;
        lspd = new Product[n];
        this.lspdd = lspdd;
        readFromFile();
    }

    public ListProduct(ListProduct other) {
        this.n = other.n;
        this.lspd = Arrays.copyOf(other.lspd, n);
        readFromFile();
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int length() {
        return n;
    }

    public Product getProduct(int i) {
        return lspd[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("Nhap so loai san pham: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    @Override
    public void display() {
        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", "Ma san pham",
                "Ten san pham", "So luong", "Don gia", "Don vi tinh", "Dung luong pin", "Loai", "Ten man hinh");
        for (Product i : lspd) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListProduct.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product i : lspd) {
                bw.write(i.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileReader fr = new FileReader("../src/data_base/ListProduct.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(", ");
                String productID = txt[0].trim();
                String productName = txt[1].trim();
                int quantity = Integer.parseInt(txt[2].trim());
                int price = Integer.parseInt(txt[3].trim());
                String unit = txt[4].trim();
                if (txt.length == 7) {
                    String pin = txt[5].trim();
                    String type = txt[6].trim();
                    add(new LapTop(productID, productName, quantity, price, unit, pin, type));
                } else if (txt.length == 6) {
                    String nameScreen = txt[5].trim();
                    add(new PC(productID, productName, quantity, price, unit, nameScreen));
                } else {
                    add(new Component(productID, productName, quantity, price, unit));
                }
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createProductID() {
        String productID;
        do {
            productID = "PD" + Static.randomID();
        } while (search(productID) != -1);

        return productID;
    }

    public String inputProductID() {
        String input;
        while (true) {
            System.out.print("Nhap ten hoac ma san pham: ");
            input = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (search(input) != -1) {
                break;
            }
            if ((input = searchByProductName(input)) != null) {
                break;
            }
            System.out.println("Ten hoac ma san pham khong dung!");
        }
        return input;
    }

    public void add() {
        Static.clearScreen();

        int choice = 0;
        do {
            System.out.println("---- MENU ----");
            System.out.println("1. Laptop");
            System.out.println("2. PC");
            System.out.println("3. Linh kien may tinh");
            System.out.print("Chon loai san pham (1-3): ");
            choice = Static.checkInputIsInt();
            Static.scanner.nextLine();

            Product x;
            switch (choice) {
                case 1:
                    x = new LapTop(productID, null, quantity, price, "cai", null, null);
                    x.input();
                    add(x);
                    break;
                case 2:
                    x = new PC(productID, null, quantity, price, "bo", null);
                    x.input();
                    add(x);
                    break;
                case 3:
                    x = new Component(productID, null, quantity, price, "cai");
                    x.input();
                    add(x);
                    break;
                default:
                    System.out.println("khong hop le!");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 3);

        lspdd.setProductID(productID);
        lspdd.setProducerID(producerID);
        if (choice == 3) {
            lspdd.select();
        } else {
            lspdd.add();
        }

        writeToFile(false);
    }

    @Override
    public void add(Product x) {
        lspd = Arrays.copyOf(lspd, n + 1);
        lspd[n++] = x;
    }

    public String getProductNameByProdcutID(String prodcutID) {
        int index = search(prodcutID);
        return lspd[index].getProductName();
    }

    public void search() {
        Static.clearScreen();

        String productID = inputProductID();
        int index = search(productID);
        search(index);

        Static.exit();
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        System.out.println("San phan:");
        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", "Ma san pham",
                "Ten san pham", "Don vi tinh", "So luong", "Don gia", "Dung luong pin", "Loai", "Ten man hinh");
        lspd[index].display();

        lspdd.search(index);
    }

    public int search(String productID) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProductID()).equals(productID)) {
                return i;
            }
        }
        return -1;
    }

    public String searchByProductName(String productName) {
        for (int i = 0; i < n; i++) {
            if ((lspd[i].getProductName()).equals(productName)) {
                return lspd[i].getProductID();
            }
        }
        return null;
    }

    public Product searchPByQuantity(int quantity) {
        for (int i = 0; i < n; i++) {
            if (lspd[i].getQuantity() == quantity) {
                return lspd[i];
            }
        }
        return null;
    }

    public Product[] searchLsByProductID(String productID) {
        int count = 0;
        Product[] ds = new Product[n];
        for (int i = 0; i < n; i++) {
            if (lspd[i].getProductID().equals(productID)) {
                ds[count++] = lspd[i];
            }
        }
        return Arrays.copyOf(ds, count);
    }

    public Product searchPByProductID(String productID) {
        for (Product sp : lspd) {
            if (sp.getProductID().equals(productID)) {
                return sp;
            }
        }
        return null;
    }

    public Product[] searchLsByAmountOfPrice(int dg1, int dg2) {
        int count = 0;
        Product[] ds = new Product[n];
        for (int i = 0; i < n; i++) {
            int donGia = lspd[i].getPrice();
            if (donGia >= dg1 && donGia <= dg2) {
                ds[count++] = lspd[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ds, count);
    }

    public void searchLsByAmountOfPrice() {
        Static.clearScreen();

        System.out.print("Chon gia thap nhat: ");
        int min = Static.checkInputIsInt();
        System.out.print("Chon gia cao nhat: ");
        int max = Static.checkInputIsInt();
        Static.scanner.nextLine();

        Product[] ds = searchLsByAmountOfPrice(min, max);
        if (ds == null) {
            System.out.println("Khong co san pham nao trong khoang gia do!");
            Static.exit();
            return;
        }

        System.out.printf(" %-15s | %-25s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s\n", "Ma san pham",
                "Ten san pham", "Don vi tinh", "So luong", "Don gia", "Dung luong pin", "Loai", "Ten man hinh");
        for (Product i : ds) {
            i.display();
        }

        Static.exit();
    }

    @Override
    public void remove() {
        Static.clearScreen();

        String productID = inputProductID();
        remove(productID);

        Static.exit();
    }

    public void remove(String productID) {
        int index = search(productID);
        remove(index);
    }

    @Override
    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        lspdd.remove(index);

        for (int i = index; i < n - 1; i++) {
            lspd[i] = lspd[i + 1];
        }
        lspd = Arrays.copyOf(lspd, n - 1);
        n--;
        System.out.println("Xoa thanh cong!");
        writeToFile(false);
    }

    public void reduceQuantity(String productID, int soLuongGiam) {
        int index = search(productID);
        if (index != -1) {
            lspd[index].setQuantity(lspd[index].getQuantity() - soLuongGiam);
        }
        writeToFile(false);
    }

    public void increaseQuantity(String productID, int quantity) {
        int index = search(productID);
        if (index != -1) {
            lspd[index].setQuantity(lspd[index].getQuantity() + quantity);
        }
        writeToFile(false);
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma san pham khong dung!");
            return;
        }

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. Ten san pham");
            System.out.println("2. Don gia");
            System.out.println("3. Don vi tinh");
            if (lspd[index] instanceof LapTop) {
                System.out.println("4. Dung luong pin");
                System.out.println("5. Loai");
            } else if (lspd[index] instanceof PC) {
                System.out.println("4. Ten man hinh");
            }
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang (1-6): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lspd[index].setProductName();
                    break;
                case 2:
                    lspd[index].setPrice();
                    break;
                case 3:

                    lspd[index].setUnit();
                    break;
                case 4:
                    if (lspd[index] instanceof LapTop) {
                        ((LapTop) lspd[index]).setPin();
                    } else if (lspd[index] instanceof PC) {
                        ((PC) lspd[index]).setNameScreen();
                    }
                    break;
                case 5:
                    if (lspd[index] instanceof LapTop) {
                        ((LapTop) lspd[index]).setType();
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Khong hop le!");
            }
        } while (choice != 6);
        lspdd.writeToFile(false);
        writeToFile(false);
    }

    public void edit(String productID) {
        int index = search(productID);
        edit(index);
    }

    @Override
    public void edit() {
        Static.clearScreen();

        String productID = inputProductID();
        edit(productID);
        lspdd.edit(productID);
    }
}