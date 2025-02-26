package element;

import control.Static;

public class Customer {
    private String customerID, firstName, lastName, address, phone;

    public Customer() {
    }

    public Customer(String customerID, String firstName, String lastName, String address, String phone) {
        setCustomerID(customerID);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhone(phone);
    }

    public Customer(Customer other) {
        setCustomerID(other.customerID);
        setFirstName(other.firstName);
        setLastName(other.lastName);
        setAddress(other.address);
        setPhone(other.phone);
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFirstName() {
        do {
            System.out.print("Nhap ho cua khach hang: ");
            setFirstName(Static.scanner.nextLine());
        } while (firstName.isEmpty());
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastName() {
        do {
            System.out.print("Nhap ten cua khach hang: ");
            setLastName(Static.scanner.nextLine());
        } while (lastName.isEmpty());
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress() {
        do {
            System.out.print("Nhap dia chi cua khach hang: ");
            setAddress(Static.scanner.nextLine());
        } while (address.isEmpty());
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhone() {
        boolean isValid;
        do {
            System.out.print("Nhap so dien thoai: ");
            phone = Static.scanner.nextLine();
            isValid = Static.checkPhone(phone);

            if (!isValid) {
                System.out.println("Vui long nhap dung dinh dang so dien thoai!");
            }

        } while (!isValid);
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void input() {
        setFirstName();

        setLastName();

        setAddress();

        setPhone();
    }

    public void display() {
        System.out.println(
                "-----------------+----------------------+-----------------+-----------------+----------------");
        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-15s%n", customerID, firstName, lastName, address,
                phone);
    }

    @Override
    public String toString() {
        return customerID + ", " + firstName + ", " + lastName + ", " + address + ", " + phone;
    }
}
