package FinTrak_Dir_Struct.dao;

import FinTrak_Dir_Struct.model.User;

public interface UserDao {
        boolean register(User user); //returns whether user registered or not
        User login(String username , String password); //For loging in the user if not registered
        User getUserById(int id);
    }
