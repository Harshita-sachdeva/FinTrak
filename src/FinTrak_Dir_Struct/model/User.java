package FinTrak_Dir_Struct.model;

public class User {
    private int UserID;
    private String UserName;
    private String UserPassword;

    public User(){}

    //Constructor for fetching Data from DB
    public User(int userID, String userName, String userPassword) {
        this.UserID = userID;
        this.UserName = userName;
        this.UserPassword = userPassword;
    }

    //Constructor for Inserting data to DB
    public User(String userName, String userPassword) {
        this.UserName = userName;
        this.UserPassword = userPassword;
    }
    //Getter n Setters
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
