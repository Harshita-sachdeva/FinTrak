package FinTrak_Dir_Struct.serviceImpl;

import FinTrak_Dir_Struct.dao.UserDao;
import FinTrak_Dir_Struct.daoImpl.UserDaoImpl;
import FinTrak_Dir_Struct.model.User;
import FinTrak_Dir_Struct.service.UserService;

public class UserServiceImpl implements UserService {
    /// Loose Coupling
    /// an object of UserDaoImpl to implement
    UserDao dao = new UserDaoImpl();

    @Override
    public boolean registerUser(String username, String password) {
        /// For various conditions
        if (username == null || password == null || username.isEmpty() || password.isEmpty()){
            return false;
    }
        return dao.register(new User(username,password));
    }


    @Override
    public User loginUser(String username, String password) {
        return dao.login(username,password);
    }

    @Override
    public User getUserById(int id) {
        return dao.getUserById(id);
    }
}
