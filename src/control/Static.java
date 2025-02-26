package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Static {
    public static Scanner scanner = new Scanner(System.in);

    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Kiểm tra biến có chứa phím cách không
    public static boolean checkSpace(String str) {
        return str != null && str.contains(" ");
    }

    // Xóa toàn bộ phím cách của chuỗi được nhập vào
    public static String inputStringNoSpace() {
        return scanner.nextLine().replaceAll("\\s+", "");
    }

    // Xóa màn hình console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Kiểm tra đầu vào của người dùng có phải kiểu Integer hay không
    public static int checkInputIsInt() {
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                break;
            } else {
                System.out.println("Khong hop le!");
                System.out.print("Xin nhap lai: ");
                scanner.next();
            }
        }
        return choice;
    }

    // Kiểm tra định dạng số điện thoại
    public static boolean checkPhone(String phoneNumber) {
        String regex = "^0\\d{9}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    // Tạo dãy số ngẫu diên gồm 3 chữ số
    public static String randomID() {
        Random random = new Random();
        long value = random.nextLong() % 10000000000L; // Số ngẫu nhiên từ 0 đến 9999999999
        return String.format("%010d", Math.abs(value));
    }

    public static void exit() {
        String exit;

        while (true) {
            System.out.print("Nhap phim x hoac enter de thoat: ");
            exit = scanner.nextLine();
            if (exit.isEmpty() || exit.equals("x"))
                break;
        }
    }

    // Kiểm tra định dạng ngày
    public static boolean checkDate(String date) {
        try {
            LocalDate.parse(date, dateFormat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Nhập ngày và đảm bảo ngày là hợp lệ
    public static String inputDate() {
        String date;
        do {
            date = scanner.nextLine();
            if (!checkDate(date)) {
                System.out.print("Ngay khong hop le! Xin nhap lai: ");
            }
        } while (!checkDate(date));
        return date;
    }

    public static String getCurrentDate() {
        LocalDate currenDate = LocalDate.now();
        return currenDate.format(dateFormat);
    }

    public static LocalDate strToLocalDate(String date) {
        return LocalDate.parse(date, dateFormat);
    }

    public static String plusMonth(String date, int month) {
        LocalDate localDate = strToLocalDate(date);
        return localDate.plusMonths(month).format(dateFormat);
    }
}
