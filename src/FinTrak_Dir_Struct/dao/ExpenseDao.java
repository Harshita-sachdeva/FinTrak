package FinTrak_Dir_Struct.dao;

import FinTrak_Dir_Struct.model.Expense;

import java.util.List;

public interface ExpenseDao {
    boolean addExpense(Expense expense); //to add the expense made by user
    List<Expense> getExpenseByUser(int UserID); //to get the expense of a particular user
    List<Expense> getExpenseByCategory(int userId , String Category); //to get the expense of a particular user (in a perticular category)
    List<Expense> getExpenseByMonth(int userIid , int month , int year); // to get the expense of a particular month of particular year
    boolean updateExpense(Expense expense); //updates if record exists and return bool value accordingly
    boolean deleteExpense(int ExpenseID); // to delete a particular expense
}
