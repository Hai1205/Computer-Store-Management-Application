package element;

import control.Static;

public class ProductDetail {
    private String productID, RAM, CPU, ROM, VGA, OS, producerID;
    private int yearProduce;

    public ProductDetail() {
    }

    public ProductDetail(String productID, String producerID, String RAM, String ROM, String CPU, String VGA,
            String OS, int yearProduce) {
        setProductID(productID);
        setRAM(RAM);
        setCPU(CPU);
        setROM(ROM);
        setVGA(VGA);
        setOS(OS);
        setProducerID(producerID);
        setYearProduce(yearProduce);
    }

    public ProductDetail(ProductDetail other) {
        setProductID(other.productID);
        setRAM(other.RAM);
        setCPU(other.CPU);
        setROM(other.ROM);
        setVGA(other.VGA);
        setOS(other.OS);
        setProducerID(other.producerID);
        setYearProduce(other.yearProduce);
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setRAM() {
        do {
            System.out.print("Nhap dung luong RAM: ");
            setRAM(Static.scanner.nextLine());
        } while (RAM.isEmpty());
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setCPU() {
        do {
            System.out.print("Nhap xung nhip CPU: ");
            setCPU(Static.scanner.nextLine());
        } while (CPU.isEmpty());
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public void setROM() {
        do {
            System.out.print("Nhap dung luong ROM: ");
            setROM(Static.scanner.nextLine());
        } while (ROM.isEmpty());
    }

    public void setVGA(String VGA) {
        this.VGA = VGA;
    }

    public void setVGA() {
        do {
            System.out.print("Nhap xung nhip VGA: ");
            setVGA(Static.scanner.nextLine());
        } while (Static.checkSpace(VGA) || VGA.isEmpty());
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public void setOS() {
        do {
            System.out.print("Nhap he dieu hanh: ");
            setOS(Static.scanner.nextLine());
        } while (Static.checkSpace(OS) || OS.isEmpty());
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public void setYearProduce(int yearProduce) {
        this.yearProduce = yearProduce;
    }

    public void setYearProduce() {
        int YeacurrentYear = Integer.parseInt(Static.getCurrentDate().substring(6, 6 + 4));
        do {
            System.out.print("Nhap nam san xuat: ");
            setYearProduce(Static.checkInputIsInt());
        } while (yearProduce < 2009 || yearProduce > YeacurrentYear);
    }

    public String getProductID() {
        return productID;
    }

    public String getRAM() {
        return RAM;
    }

    public String getCPU() {
        return CPU;
    }

    public String getROM() {
        return ROM;
    }

    public String getVGA() {
        return VGA;
    }

    public String getOS() {
        return OS;
    }

    public String getProducerID() {
        return producerID;
    }

    public int getYearProduce() {
        return yearProduce;
    }

    public void input() {
        setRAM();

        setROM();

        setCPU();

        setVGA();

        setOS();

        setYearProduce();
    }

    public void display() {
        System.out.println(
                "-----------------+-----------------+----------------------+----------------------+----------------------+----------------------+-----------------+------------------");
        System.out.format(" %-15s | %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s%n", productID, producerID,
                RAM, ROM, CPU, VGA, OS, yearProduce);
    }

    @Override
    public String toString() {
        return productID + ", " + producerID + ", " + RAM + ", " + ROM + ", " + CPU + ", " + VGA + ", " + OS
                + ", " + yearProduce;
    }

}
