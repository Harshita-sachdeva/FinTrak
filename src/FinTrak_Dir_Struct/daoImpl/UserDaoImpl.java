package FinTrak_Dir_Struct.daoImpl;

import FinTrak_Dir_Struct.dao.UserDao;
import FinTrak_Dir_Struct.db.DBConnection;
import FinTrak_Dir_Struct.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean register(User user){
       try(Connection conn= DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement("INSERT INTO User(UserName , UserPassword) VALUES (?,?);")){
           ps.setString(1,user.getUserName());
           ps.setString(2,user.getUserPassword());
           return ps.executeUpdate()>0; //returns true if user registered successfully(no. of rows get affected) ... else false
       }
       catch(SQLException e){
           System.out.println("❗Register failed : "+e.getMessage());
           return false;
       }
    }

    @Override
    public User login(String Username , String password){
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM User WHERE UserName=? AND UserPassword =?;")){
            ps.setString(1,Username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("UserID"),rs.getString("UserName"),rs.getString("UserPassword"));
            }

        }
        catch(SQLException e){
            System.out.println("❗Login failed : "+e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("SELECT * FROM User WHERE UserID=?;")){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new User(rs.getInt("UserID"),rs.getString("UserName"),rs.getString("UserPassword"));

            }

        }
        catch(SQLException e){
            System.out.println("❗Getting User By ID failed  : "+e.getMessage());
        }
        return null;
    }

}
