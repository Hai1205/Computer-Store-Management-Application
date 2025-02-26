package list;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

import control.*;
import element.*;

public class ListEmployee implements InterfaceList<Employee> {
    private Employee[] lsem;
    private int n;

    private String employeeID;

    public ListEmployee() {
        n = 0;
        lsem = new Employee[n];
        readFromFile();
    }

    public ListEmployee(ListEmployee other) {
        this.n = other.n;
        this.lsem = Arrays.copyOf(other.lsem, n);
        readFromFile();
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public int length() {
        return n;
    }

    public Employee getEmployee(int i) {
        return lsem[i];
    }

    public void input() {
        Static.clearScreen();

        System.out.print("So luong nhan vien: ");
        int m = Static.checkInputIsInt();
        Static.scanner.nextLine();

        for (int i = 0; i < m; i++) {
            add();
        }
    }

    public void display() {
        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-5s | %-10s%n", "Ma nhan vien", "Ho nhan vien",
                "Ten nhan vien", "Ngay sinh", "KPI", "Luong");
        for (Employee i : lsem) {
            i.display();
        }

        Static.exit();
    }

    public void writeToFile(boolean dontDeleteData) {
        try {
            FileWriter fw = new FileWriter("../src/data_base/ListEmployee.txt", dontDeleteData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Employee i : lsem) {
                if (i != null) {
                    bw.write(i.toString());
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch (Exception em) {
            em.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileReader fr = new FileReader("../src/data_base/ListEmployee.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] txt = line.split(",");
                if (txt.length == 6) {
                    String employeeID = txt[0].trim();
                    String firstName = txt[1].trim();
                    String lastName = txt[2].trim();
                    String DOB = txt[3].trim();
                    int KPI = Integer.parseInt(txt[4].trim());
                    int salary = Integer.parseInt(txt[5].trim());
                    add(new Employee(employeeID, firstName, lastName, DOB, KPI, salary));
                }
            }
            br.close();
            fr.close();
        } catch (Exception em) {
            em.printStackTrace();
        }
    }

    public String createEmployeeID() {
        String employeeID;
        do {
            employeeID = "EM" + Static.randomID();
        } while (search(employeeID) != -1);

        return employeeID;
    }

    public String inputEmployeeID() {
        String input;
        while (true) {
            System.out.print("Nhap ten hoac ma nhan vien: ");
            input = Static.scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (search(input) != -1) {
                break;
            }
            if ((input = searchByFullName(input)) != null) {
                break;
            }
            System.out.println("Ten hoac ma nhan vien khong dung!");
        }
        return input;
    }

    public String autoSelectemployeeID() {
        Random random = new Random();
        int index = random.nextInt(n);
        return lsem[index].getEmployeeID();
    }

    public void add() {
        Static.clearScreen();

        lsem = Arrays.copyOf(lsem, n + 1);
        System.out.println("Thong tin nhan vien: ");

        if (employeeID == null || employeeID.isEmpty()) {
            employeeID = createEmployeeID();
        }

        lsem[n] = new Employee();
        lsem[n].setEmployeeID(employeeID);
        lsem[n++].input();
        writeToFile(false);

        Static.exit();
    }

    public void add(Employee x) {
        lsem = Arrays.copyOf(lsem, n + 1);
        lsem[n++] = x;
        writeToFile(false);
    }

    public void search() {
        Static.clearScreen();

        String employeeID = inputEmployeeID();
        int index = search(employeeID);
        search(index);

        Static.exit();
    }

    public String searchByFullName(String fullName) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getFullName()).equals(fullName)) {
                return lsem[i].getEmployeeID();
            }
        }
        return null;
    }

    public void search(int index) {
        if (index == -1) {
            System.out.println("Ma nhan vien khong dung!");
            return;
        }

        System.out.format(" %-15s | %-20s | %-15s | %-15s | %-5s | %-10s%n", "Ma nhan vien", "Ho nhan vien",
                "Ten nhan vien", "Ngay sinh", "KPI", "Luong");
        lsem[index].display();
    }

    public int search(String employeeID) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getEmployeeID()).equals(employeeID)) {
                return i;
            }
        }
        return -1;
    }

    public Employee searchEByEmployeeID(String employeeID) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getEmployeeID()).equals(employeeID)) {
                return lsem[i];
            }
        }
        return null;
    }

    public Employee searchEByFirstName(String firstName) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getFirstName()).equals(firstName)) {
                return lsem[i];
            }
        }
        return null;
    }

    public Employee searchEByLastName(String lastName) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getLastName()).equals(lastName)) {
                return lsem[i];
            }
        }
        return null;
    }

    public Employee searchEByDOB(String DOB) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getDOB()).equals(DOB)) {
                return lsem[i];
            }
        }
        return null;
    }

    public Employee searchEBySalary(int salary) {
        for (int i = 0; i < n; i++) {
            if ((lsem[i].getSalary()) == salary) {
                return lsem[i];
            }
        }
        return null;
    }

    public Employee[] searchLsByEmployeeID(String employeeID) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            if (lsem[i].getEmployeeID().equals(employeeID)) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsByFirstName(String firstName) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            if (lsem[i].getFirstName().equals(firstName)) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsByLastName(String lastName) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            if (lsem[i].getLastName().equals(lastName)) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsByDOB(String DOB) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            if (lsem[i].getDOB().equals(DOB)) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsBySalary(int salary) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            if (lsem[i].getSalary() == salary) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsByOverTime(String dateBegin, String dateEnd) {
        int count = 0;
        Employee[] ls = new Employee[n];

        LocalDate date1 = LocalDate.parse(dateBegin, Static.dateFormat);
        LocalDate date2 = LocalDate.parse(dateEnd, Static.dateFormat);

        if (date1.isAfter(date2))
            return null;

        for (int i = 0; i < n; i++) {
            LocalDate ngay = LocalDate.parse(lsem[i].getDOB(), Static.dateFormat);
            if (!ngay.isBefore(date1) && !ngay.isAfter(date2)) {
                ls[count++] = lsem[i];
            }
        }

        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public Employee[] searchLsByAmountOfSalary(int salary1, int salary2) {
        int count = 0;
        Employee[] ls = new Employee[n];
        for (int i = 0; i < n; i++) {
            long salary = lsem[i].getSalary();
            if (salary >= salary1 && salary <= salary2) {
                ls[count++] = lsem[i];
            }
        }
        if (count == 0)
            return null;
        return Arrays.copyOf(ls, count);
    }

    public void remove() {
        Static.clearScreen();

        String employeeID = inputEmployeeID();
        remove(employeeID);

        Static.exit();
    }

    public void remove(int index) {
        if (index == -1) {
            System.out.println("Ma nhan vien khong dung!");
            return;
        }

        for (int i = index; i < n - 1; i++) {
            lsem[i] = lsem[i + 1];
        }
        lsem = Arrays.copyOf(lsem, n - 1);
        n--;
        System.out.println("xoa thanh cong!");

        writeToFile(false);
    }

    public void remove(String employeeID) {
        int index = search(employeeID);
        remove(index);
    }

    public void reduceKPI(String employeeID) {
        int index = search(employeeID);
        if (index != -1) {
            Employee em = getEmployee(index);
            em.setKPI(Math.max(em.getKPI() - 1, 0));
            writeToFile(false);
        }
    }

    public void increaseKPI(String employeeID) {
        int index = search(employeeID);
        if (index != -1) {
            Employee em = getEmployee(index);
            em.setKPI(em.getKPI() + 1);
            writeToFile(false);
        }
    }

    public void edit(int index) {
        if (index == -1) {
            System.out.println("Ma nhan vien khong dung!");
            return;
        }

        int choice;
        do {
            Static.clearScreen();

            System.out.println("---- MENU ----");
            System.out.println("1. Ho nhan vien");
            System.out.println("2. Ten nhan vien");
            System.out.println("3. Ngay sinh");
            System.out.println("4. Thoat");
            System.out.print("Chon chuc nang (1-4): ");
            choice = Static.scanner.nextInt();
            Static.scanner.nextLine();

            switch (choice) {
                case 1:
                    lsem[index].setFirstName();
                    break;
                case 2:
                    lsem[index].setLastName();
                    break;
                case 3:
                    lsem[index].setDOB();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Khong hop le!");
            }
        } while (choice != 4);
        writeToFile(false);
    }

    public void edit() {
        Static.clearScreen();

        String employeeID = inputEmployeeID();
        edit(employeeID);

        Static.exit();
    }

    public void edit(String employeeID) {
        int index = search(employeeID);
        edit(index);
    }
}