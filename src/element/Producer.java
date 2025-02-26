package element;

import control.Static;

public class Producer {
    private String producerID, producerName;

    public Producer() {
    }

    public Producer(String producerID, String producerName) {
        setProducerID(producerID);
        setProducerName(producerName);
    }

    public Producer(Producer other) {
        setProducerID(other.producerID);
        setProducerName(other.producerName);
    }

    public void setProducerID(String producerID) {
        this.producerID = producerID;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public void setProducerName() {
        do {
            System.out.print("Nhap ten nha san xuat: ");
            setProducerName(Static.scanner.nextLine());
        } while (producerName.isEmpty() || Static.checkSpace(producerName));
    }

    public String getProducerID() {
        return producerID;
    }

    public String getProducerName() {
        return producerName;
    }

    public void input() {
        setProducerName();
    }

    public void display() {
        System.out.println("-----------------+---------------------");
        System.out.format(" %-15s | %-20s%n", producerID, producerName);
    }

    @Override
    public String toString() {
        return producerID + ", " + producerName;
    }
}
