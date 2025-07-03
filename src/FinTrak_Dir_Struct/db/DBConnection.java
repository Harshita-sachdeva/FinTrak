package FinTrak_Dir_Struct.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/expense_tracker";
    private static final String Username="root";
    private static final String password="root";


    public static Connection getConnection(){
        try{
            //Establishing Connection
            return DriverManager.getConnection(URL,Username,password);
        }
        catch(SQLException e){
            System.out.println("DB Connection Failed: "+e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws SQLException , ClassNotFoundException{
        // loading the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
