package teleDemo.entities;

public class UserRegist<T> {
    public String userName;
    public String password;
    public int result;
    public String msg;

    public UserRegist(String userName, String password,int result,String msg){
        this.userName=userName;
        this.password=password;
        this.result=result;
        this.msg=msg;
    }
    public UserRegist(){}

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}
//
//    public String getPhone_number(){return phone_number;}
//
//    public void setPhone_number(String phone_number) {this.phone_number = phone_number;}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
