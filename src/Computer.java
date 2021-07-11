import java.io.Serializable;
import java.util.ArrayList;

public class Computer implements Serializable, Runnable {
    private int id;
    private boolean status = false;
    private int time = 0;

    public Computer() {
    }

    public ArrayList<String> listNameService = new ArrayList<>();
    public ArrayList<Integer> listPrice = new ArrayList<>();

    public Computer(int id, boolean status, int time) {
        this.id = id;
        this.status = status;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPriceService() {
        int sum = 0;
        for (int i = 0; i < listPrice.size(); i++) {
            sum += listPrice.get(i);
        }
        return sum;
    }

    public String getStatus() {
        if (status == true) {
            return "Online";
        } else return "Offline";
    }

    public int getPrice() {
        return time * 5000 + getPriceService();
    }

    @Override
    public String toString() {
        return "COM" + getId() +" "+getStatus();
    }

    @Override
    public void run() {
        while (true) {
            if (isStatus() == true) {

                time += 1;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                time = 0;
                break;
            }
        }
    }
}