import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Manager {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Computer> listCOM = new ArrayList<>();
    public static ArrayList<ServiceOption> listService = new ArrayList<>();
    public static ArrayList<DoanhThuTheoNgay> listDoanhThuTheoNgay = new ArrayList<>();
    public static int doanhThu = 0;
    public static int countCOM = 5;
//    static {
//        try {
//            listCOM = readlistCOM();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    static {
//        try {
//            listService = readlistService();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//

//
//
//    static {
//        try {
//            listDoanhThuTheoNgay = readlistDoanhThu();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


    public static ArrayList<Computer> readlistCOM() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("computer.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<Computer>) objectInputStream.readObject();
    }

    public static void writelistCOM() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("computer.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listCOM);
    }

    public static ArrayList<ServiceOption> readlistService() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("service.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<ServiceOption>) objectInputStream.readObject();
    }

    public static void writelistService() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("service.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listService);
    }

    public static ArrayList<DoanhThuTheoNgay> readlistDoanhThu() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("doanhthu.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<DoanhThuTheoNgay>) objectInputStream.readObject();
    }

    public static void writelistDoanhTHu() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("doanhthu.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listDoanhThuTheoNgay);
    }

    public String showDichvu(int a) {
        String show = "";
        for (int i = 0; i < listCOM.get(a).listNameService.size(); i++) {
            show += listCOM.get(a).listNameService.get(i);
            show += ":";
            show += listCOM.get(a).listPrice.get(i);
            show+="|";
        }
        return show;
    }

    public void showOnline() {
        int check = -1;
        for (int a = 0; a < listCOM.size(); a++) {
            if (listCOM.get(a).isStatus() == true) {
                check = 1;
                break;
            }
        }
        if (check < 0) {
            System.out.println("Chưa có máy nào được bật");
        } else {
            for (int i = 0; i < listCOM.size(); i++) {
                if (listCOM.get(i).isStatus() == true) {
                    System.out.println(listCOM.get(i).getId() + "." + "COM" + listCOM.get(i).getId() + " Online");
                }
            }
            System.out.println("Chon may de xem chi tiet");
            int chooseOnline = scanner.nextInt();
            for (int j = 0; j < listCOM.size(); j++) {
                if (chooseOnline == listCOM.get(j).getId()) {
                    System.out.println(listCOM.get(j) +"\n"+ "Time: " + listCOM.get(j).getTime() +"\n"+ "Dịch vụ: " + showDichvu(j) + "\n"+"Tiền máy hiện tại: " + listCOM.get(j).getPrice());
                }
            }
        }
    }


    public void openCOM() throws IOException {
        for (int i = 0; i < listCOM.size(); i++) {
            if (listCOM.get(i).isStatus() == false) {
                System.out.println(listCOM.get(i).getId() + "." + "COM" + listCOM.get(i).getId() + " Offline");
            }
        }
        System.out.println("chon so may can bat");
        int chooseOffline = scanner.nextInt();
        int check = -1;
        for (int j = 0; j < listCOM.size(); j++) {
            if (chooseOffline == listCOM.get(j).getId()) {
                check = j;
            }
        }
        if (check < 0) {
            System.out.println("Máy ko tồn tại");
        } else {
            listCOM.get(check).setStatus(true);
            Thread thread = new Thread(listCOM.get(check));
            thread.start();
            System.out.println("COM" + chooseOffline + " đã bật");
            writelistCOM();
        }

    }


    public void add() throws IOException {
        countCOM++;
        System.out.println("Đã thêm COM" + countCOM);
        listCOM.add(new Computer(countCOM, false, 0));
        writelistCOM();
    }

    public void edit() throws IOException {
        System.out.println("Nhập máy cần đổi");
        int id1 = scanner.nextInt();
        System.out.println("Nhập máy bị đổi");
        int id2 = scanner.nextInt();
        int check1 = 0;
        for (int i = 0; i < listCOM.size(); i++) {
            if (listCOM.get(i).getId() == id1) {
                check1 = i;
            }
        }
        int check2 = 0;
        for (int i = 0; i < listCOM.size(); i++) {
            if (listCOM.get(i).getId() == id2) {
                check2 = i;
            }
        }
        listCOM.get(check2).setStatus(listCOM.get(check1).isStatus());
        listCOM.get(check2).setTime(listCOM.get(check1).getTime());
        Thread thread = new Thread(listCOM.get(check2));
        thread.start();
        for (int i = 0; i < listCOM.get(check1).listPrice.size(); i++) {
            listCOM.get(check2).listPrice.add(listCOM.get(check1).listPrice.get(i));
        }
        for (int i = 0; i < listCOM.get(check1).listNameService.size(); i++) {
            listCOM.get(check2).listNameService.add(listCOM.get(check1).listNameService.get(i));
        }
        listCOM.get(check1).listNameService = new ArrayList<>();
        listCOM.get(check1).listPrice = new ArrayList<>();
        listCOM.get(check1).setStatus(false);
        listCOM.get(check1).setTime(0);
        System.out.println("COM" + id1 + " đã chuyên sang COM" + id2);
        writelistCOM();
    }

    public void remove() throws IOException {
        for (int i = 0; i < listCOM.size(); i++) {
            System.out.println("COM" + listCOM.get(i).getId());
        }
        System.out.println("Chọn máy cần xoá");
        int choose = scanner.nextInt();
        for (int j = 0; j < listCOM.size(); j++) {
            if (choose == listCOM.get(j).getId()) {
                listCOM.remove(j);
            }
        }
        System.out.println("COM" + choose + " đã xoá");
        writelistCOM();
    }

    public void addServiceOption() throws IOException {
        System.out.println("Nhap ten Dich vu");
        String nameService = scanner.next();
        System.out.println("Nhap gia");
        int priceService = scanner.nextInt();
        listService.add(new ServiceOption(nameService, priceService));
        System.out.println("dịch vụ đã được thêm vào");
        writelistService();
    }

    public void showListService() {
        for (int i = 0; i < listService.size(); i++) {
            System.out.println((i + 1) + " COM" + listService.get(i));
        }
    }

    public void addServiceForCOM() throws IOException {

        System.out.println("Them dich vu");


        for (int i = 0; i < listCOM.size(); i++) {
            if (listCOM.get(i).isStatus() == true) {
                System.out.println((i) + ". COM" + listCOM.get(i).getId() + " online");

            }
        }
        int chooseThemDichVu = scanner.nextInt();
        int chooseService1 = 0;
        for (int j = 0; j < listCOM.size(); j++) {
            if (chooseThemDichVu == j) {
                showListService();
                int chooseService = scanner.nextInt();
                chooseService1 = chooseService;
                for (int k = 0; k < listService.size(); k++) {
                    if (chooseService == k + 1) {
                        listCOM.get(chooseThemDichVu).listNameService.add(listService.get(k).getNameService());
                        listCOM.get(chooseThemDichVu).listPrice.add(listService.get(k).getPriceService());
                    }
                }
            }
        }
        System.out.println(listService.get(chooseService1 - 1) + " đã được thêm vào COM" + chooseThemDichVu);
        writelistCOM();
    }

    public void letPrice() throws IOException {
        System.out.println("Tinh tien");

        for (int i = 0; i < listCOM.size(); i++) {
            if (listCOM.get(i).isStatus() == true) {
                System.out.println("COM" + i + " online");

            }
        }
        System.out.println("Nhap may can tra tien");
        int chooseTinhTien = scanner.nextInt();
        for (int j = 0; j < listCOM.size(); j++) {
            if (chooseTinhTien == j) {
                System.out.println("so tien la " + listCOM.get(chooseTinhTien).getPrice());
                doanhThu += listCOM.get(chooseTinhTien).getPrice();
                listCOM.get(chooseTinhTien).setStatus(false);
                listCOM.get(chooseTinhTien).listPrice = new ArrayList<>();
                listCOM.get(chooseTinhTien).listNameService = new ArrayList<>();

                luuDoanhThuTheoNgay();
            }
        }
    }

    public void getDoanhThu() {
        System.out.println("doanh thu la " + doanhThu);
    }

    public static void luuDoanhThuTheoNgay() throws IOException {
        LocalDate dayHienTai = LocalDate.now();
        int dayOfYear = dayHienTai.getDayOfYear();
        for (int i = 0; i < listDoanhThuTheoNgay.size(); i++) {
            if (listDoanhThuTheoNgay.get(i).getNumberDay() == dayOfYear) {
                listDoanhThuTheoNgay.get(i).setDoanhThu(doanhThu);
            }
        }
        writelistDoanhTHu();
    }

    public static void resetDoanhThu() throws IOException {
        LocalDate date = LocalDate.now();
        int dayOfYear = date.getDayOfYear();
        if (listDoanhThuTheoNgay.size() == 0) {
            listDoanhThuTheoNgay.add(new DoanhThuTheoNgay(dayOfYear, doanhThu));
        }
        for (int i = 0; i < listDoanhThuTheoNgay.size(); i++) {
            if (listDoanhThuTheoNgay.get(i).getNumberDay() != dayOfYear) {
                doanhThu = 0;
                listDoanhThuTheoNgay.add(new DoanhThuTheoNgay(dayOfYear, doanhThu));
            }
        }
        writelistDoanhTHu();
    }
}



