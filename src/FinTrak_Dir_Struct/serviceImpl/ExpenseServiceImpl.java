package FinTrak_Dir_Struct.serviceImpl;

import FinTrak_Dir_Struct.dao.ExpenseDao;
import FinTrak_Dir_Struct.daoImpl.ExpenseDaoImpl;
import FinTrak_Dir_Struct.model.Expense;
import FinTrak_Dir_Struct.service.ExpenseService;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    ExpenseDao expenseDao=new ExpenseDaoImpl();
    @Override
    public boolean addExpense(Expense expense) {
        return expenseDao.addExpense(expense);
    }

    @Override
    public List<Expense> getExpenses(int UserID) {
        return expenseDao.getExpenseByUser(UserID);

    }

    @Override
    public List<Expense> getExpensesByCategory(int UserId, String Category) {
        return expenseDao.getExpenseByCategory(UserId,Category);
    }

    @Override
    public List<Expense> getExpenseByMonth(int UserId, int month, int year) {
        return expenseDao.getExpenseByMonth(UserId,month,year);
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return expenseDao.updateExpense(expense);
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        return expenseDao.deleteExpense(expenseId);
    }
}
