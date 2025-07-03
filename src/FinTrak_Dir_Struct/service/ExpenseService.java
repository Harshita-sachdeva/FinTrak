package FinTrak_Dir_Struct.service;

import FinTrak_Dir_Struct.model.Expense;

import java.util.List;

public interface ExpenseService {
    //methods working like helpers -- calling
    boolean addExpense(Expense expense);
    List<Expense> getExpenses(int UserID);
    List<Expense> getExpensesByCategory(int UserId , String Category);
    List<Expense> getExpenseByMonth(int UserId, int month , int year);
    boolean updateExpense(Expense expense);
    boolean deleteExpense(int expenseId);
}
