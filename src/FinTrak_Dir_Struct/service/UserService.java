package FinTrak_Dir_Struct.service;

import FinTrak_Dir_Struct.model.User;

public interface UserService {
    boolean registerUser(String username , String password);  ///takes the arguments n calls UserDao.register
    User loginUser(String username , String password);        /// a service for calling UserDao.login
    User getUserById(int id);                                 /// a service for calling UserDao.getUserById
}
