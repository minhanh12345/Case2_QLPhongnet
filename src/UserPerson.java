import java.io.Serializable;

public class UserPerson implements Serializable {
    private String userName;
    private String passWord;
    public UserPerson(){}

    @Override
    public String toString() {
        return "UserPerson{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserPerson(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
