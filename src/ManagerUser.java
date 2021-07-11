import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerUser {
    static Scanner scanner=new Scanner(System.in);
    public static ArrayList<UserPerson> listUser=new ArrayList<>();

    static {
        try {
            listUser = read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<UserPerson> read() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream=new FileInputStream("user.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        return (ArrayList<UserPerson>) objectInputStream.readObject();

    }
    public static void write() throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("user.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listUser);
    }

}
