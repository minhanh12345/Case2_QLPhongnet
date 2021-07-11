import java.io.Serializable;

public class DoanhThuTheoNgay implements Serializable {
    private int numberDay;
    private int doanhThu;
    public DoanhThuTheoNgay(){}

    public DoanhThuTheoNgay(int numberDay, int doanhThu) {
        this.numberDay = numberDay;
        this.doanhThu = doanhThu;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return "DoanhThuTheoNgay{" +
                "numberDay=" + numberDay +
                ", doanhThu=" + doanhThu +
                '}';
    }
}
