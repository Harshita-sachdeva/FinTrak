package FinTrak_Dir_Struct.FinTrakUI;

import FinTrak_Dir_Struct.service.UserService;
import FinTrak_Dir_Struct.serviceImpl.UserServiceImpl;

public class MainApp {
    public static void main(String[] args) {
        System.out.printf("================================="+" Welcome to FinTrak 💸 "+"=================================");
        UserService userService=new UserServiceImpl();
        MenuUI menu=new MenuUI(userService);
        menu.homeMenu(); //Login / Register First
    }
}
