import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String nameFile1;
    static String passWord1;
    static Scanner scanner = new Scanner(System.in);

    public static void showCOM() {
        for (Computer com : Manager.listCOM) {
            System.out.println(com);
        }
    }

    public static void start() throws IOException, ClassNotFoundException {


        File file = new File("user.txt");

        if (!file.exists()) {
            ManagerUser.listUser.add(new UserPerson("new", "new"));
            ManagerUser.write();
        }


        System.out.println("--------------");
        System.out.println("1.Dang Nhap  |");
        System.out.println("2 Dang ky    |");
        System.out.println("--------------");
        int choose = Integer.parseInt(scanner.nextLine());
        if (choose == 2) {
            while (true) {
                System.out.println("Nhap username");
                String userName = scanner.nextLine();
                int check = -1;

                for (int i = 0; i < ManagerUser.listUser.size(); i++) {
                    if (ManagerUser.listUser.get(i).getUserName().equals(userName)) {
                        check = i;
                    }
                }
                if (check > 0) {
                    System.out.println("da co ");
                } else {
                    System.out.println("Nhap password");
                    String password = scanner.nextLine();
                    UserPerson userPerson = new UserPerson(userName, password);
                    ManagerUser.listUser.add(userPerson);
                    ManagerUser.write();
                    nameFile1 = userName;
                    System.out.println("WELCOME " + userName);
                    passWord1 = password;
                    break;
                }
            }
        } else {
            int check1 = -1;
            while (true) {
                System.out.println("Nhap username");
                String userName = scanner.nextLine();
                for (int i = 0; i < ManagerUser.listUser.size(); i++) {
                    if (ManagerUser.listUser.get(i).getUserName().equals(userName)) {
                        while (true) {
                            System.out.println("Nhap Password");
                            String password = scanner.nextLine();
                            if (ManagerUser.listUser.get(i).getPassWord().equals(password)) {
                                nameFile1 = userName;
                                System.out.println("WELCOME " + userName);
                                check1 = 1;
                                passWord1 = password;
                                break;
                            }
                            System.out.println("sai pass");
                        }
                    }
                }
                if (check1 > 0) {
                    break;
                }
                System.out.println("sai user");
            }
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        start();
        Manager manager = new Manager();
//        File fileCom = new File("computer.txt");
//        File fileService = new File("service.txt");
//        if (!fileCom.exists()) {
            Computer com0 = new Computer(0, false, 0);
            Computer com1 = new Computer(1, false, 0);
            Computer com2 = new Computer(2, false, 0);
            Computer com3 = new Computer(3, false, 0);
            Computer com4 = new Computer(4, false, 0);
            Computer com5 = new Computer(5, false, 0);
            Manager.listCOM.add(com0);
            Manager.listCOM.add(com1);
            Manager.listCOM.add(com2);
            Manager.listCOM.add(com3);
            Manager.listCOM.add(com4);
            Manager.listCOM.add(com5);
//        }
//        if (!fileService.exists()) {
            Manager.listService.add(new ServiceOption("coca", 100));
            Manager.listService.add(new ServiceOption("sting", 50));
            Manager.listService.add(new ServiceOption("redbull", 70));
            Manager.listService.add(new ServiceOption("mitom", 80));
//        }

        while (true) {
            System.out.println("__________________________________________" +
                    "");
            System.out.println("Hệ thống COMPUTER : số máy: " + Manager.listCOM.size());
            showCOM();
            System.out.println("___________________________________________");
            System.out.println("0.Mở máy");
            System.out.println("1.Hiển thị máy đang online");
            System.out.println("2.Thêm máy mới");
            System.out.println("3.Xoá máy");
            System.out.println("4.Thêm dịch vụ vào hệ thống");
            System.out.println("5.Thêm dịch vụ vào máy");
            System.out.println("6.Tính tiền");
            System.out.println("7.Xem doanh thu hôm nay");
            System.out.println("8.Đổi máy");
            System.out.println("9.Xem tổng doanh thu theo ngày");
            System.out.println("10.Xoá tài khoản");
            System.out.println("11.Thay đổi password");
            System.out.println("12.Exit");
            int choose = scanner.nextInt();
            Manager.resetDoanhThu();
            switch (choose) {
                case 0:
                    manager.openCOM();
                    break;
                case 1:
                    manager.showOnline();
                    break;
                case 2:
                    manager.add();
                    break;
                case 3:
                    manager.remove();
                    break;
                case 4:
                    manager.addServiceOption();
                    break;
                case 5:
                    manager.addServiceForCOM();
                    break;
                case 6:
                    manager.letPrice();
                    break;
                case 7:
                    manager.getDoanhThu();
                    break;
                case 8:
                    manager.edit();
                    break;
                case 9:
                    ManagerDate.doanhThuTheoTime();
                    break;
                case 10:
                    for (int i = 0; i < ManagerUser.listUser.size(); i++) {
                        if (ManagerUser.listUser.get(i).getUserName().equals(nameFile1)) {
                           ManagerUser.listUser.remove(i);
                        }
                    }
                   ManagerUser.write();
                    System.out.println("đã xoá tài khoản");
                    break;
                case 11:
                    System.out.println("Nhập pass cần thay đổi");
                    String pass = scanner.next();
                    for (int i = 0; i < ManagerUser.listUser.size(); i++) {
                        if (ManagerUser.listUser.get(i).getUserName().equals(nameFile1)) {
                            ManagerUser.listUser.get(i).setPassWord(pass);
                        }
                    }
                    System.out.println("Đã thay đổi");
                    ManagerUser.write();
                    break;
                case 12:
                    System.exit(0);
            }
        }
    }
}
