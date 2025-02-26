package element;

import control.Static;;

public class Employee {
    private String employeeID, firstName, lastName, DOB;
    private int KPI, salary;

    public Employee() {
    }

    public Employee(String employeeID, String firstName, String lastName, String DOB, int KPI, int salary) {
        setEmployeeID(employeeID);
        setFirstName(firstName);
        setLastName(lastName);
        setDOB(DOB);
        setKPI(KPI);
        setSalary(salary);
    }

    public Employee(Employee other) {
        setEmployeeID(other.employeeID);
        setFirstName(other.firstName);
        setLastName(other.lastName);
        setDOB(other.DOB);
        setKPI(other.KPI);
        setSalary(other.salary);
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFirstName() {
        do {
            System.out.print("Nhap ho cua nhan vien: ");
            setFirstName(Static.scanner.nextLine());
        } while (firstName.isEmpty());
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLastName() {
        do {
            System.out.print("Nhap ten cua nhan vien: ");
            setLastName(Static.scanner.nextLine());
        } while (lastName.isEmpty());
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setDOB() {
        System.out.print("Nhap ngay sinh cua nhan vien: ");
        setDOB(Static.inputDate());
    }

    public void setKPI(int KPI) {
        this.KPI = KPI;
        setSalary();
    }

    public void setSalary() {
        int bonus = 0;
        int maxKPI = 10;
        if (KPI >= maxKPI) {
            bonus = 50 + 50 * (KPI - maxKPI) / 10;
        }
        setSalary(3000 + bonus);
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmployeeID() {
        return employeeID;
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

    public String getDOB() {
        return DOB;
    }

    public int getKPI() {
        return KPI;
    }

    public int getSalary() {
        return salary;
    }

    public void input() {
        setFirstName();

        setLastName();

        setDOB();

        setSalary();
    }

    public void display() {
        System.out.println(
                "-----------------+----------------------+-----------------+-----------------+-------+------------");
        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-5s | %-10s%n", employeeID, firstName, lastName, DOB, KPI,
                salary);
    }

    @Override
    public String toString() {
        return employeeID + ", " + firstName + ", " + lastName + ", " + DOB + ", " + KPI + ", " + salary;
    }
}
