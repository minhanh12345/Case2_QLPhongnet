import java.io.Serializable;

public class ServiceOption implements Serializable {
    private String nameService;
    private int priceService;
    public ServiceOption(){}

    public ServiceOption(String nameService, int priceService) {
        this.nameService = nameService;
        this.priceService = priceService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }

    @Override
    public String toString() {
        return "ServiceOption{" +
                "nameService='" + nameService + '\'' +
                ", priceService=" + priceService +
                '}';
    }
}
