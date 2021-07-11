import java.util.ArrayList;
import java.util.Scanner;

public class ManagerDate {
    static Scanner scanner = new Scanner(System.in);

    public static void doanhThuTheoTime() {
        int[] dateByMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        System.out.println("Nhap ngay bat dau");
        int dayStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap thang bat dau");
        int monthStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap ngay ket thuc");
        int dayEnd = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhap thang ket thuc");
        int monthEnd = Integer.parseInt(scanner.nextLine());
        int numberDayStart = 0;
        for (int i = 0; i < monthStart - 1; i++) {
            numberDayStart += dateByMonth[i];
        }
        numberDayStart += dayStart;
        int numberDayEnd = 0;
        for (int i = 0; i < monthEnd - 1; i++) {
            numberDayEnd += dateByMonth[i];
        }
        numberDayEnd += dayEnd;
        int doanhThuTong = 0;

        while (numberDayStart <= numberDayEnd) {
            for (int i = 0; i < Manager.listDoanhThuTheoNgay.size(); i++) {
                if (Manager.listDoanhThuTheoNgay.get(i).getNumberDay() == numberDayStart) {
                    doanhThuTong += Manager.listDoanhThuTheoNgay.get(i).getDoanhThu();
                }
            }
            numberDayStart++;
        }
        System.out.println("Doanh thu tong la : " + doanhThuTong);
    }
}
