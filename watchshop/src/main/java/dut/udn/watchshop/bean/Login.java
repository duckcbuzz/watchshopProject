package dut.udn.watchshop.bean;


import lombok.*;

@Setter
@Getter
@Data
public class Login {

    String userName;
    String password;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

}
