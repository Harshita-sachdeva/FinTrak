package FinTrak_Dir_Struct.daoImpl;

import FinTrak_Dir_Struct.dao.ExpenseDao;
import FinTrak_Dir_Struct.db.DBConnection;
import FinTrak_Dir_Struct.model.Expense;
import FinTrak_Dir_Struct.model.User;
import FinTrak_Dir_Struct.service.ExpenseService;
import FinTrak_Dir_Struct.service.UserService;
import FinTrak_Dir_Struct.serviceImpl.ExpenseServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import FinTrak_Dir_Struct.FinTrakUI.MenuUI;

public class ExpenseDaoImpl implements ExpenseDao {

    @Override
    public boolean addExpense(Expense expense) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Expense (user_id , Category , Amount , ExpenseDate , ExpenseDescription ) VALUES(?,?,?,?,?);")) {
            System.out.println("Inserting expense for user_id = "+expense.getUser_id());
            ps.setInt(1,expense.getUser_id());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setDate(4, Date.valueOf(expense.getExpenseDate()));
            ps.setString(5, expense.getExpenseDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Add expense failed : " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Expense> getExpenseByUser(int user_id) {
        String sql = "SELECT * FROM Expense WHERE user_id=?;";
        return getExpenses(sql, ps -> ps.setInt(1,user_id));
    }

//    public List<Expense> getExpenseByCategory(int UserID,String Category) {
//        String sql = "SELECT * FROM expenses WHERE user_id=? AND Category=?;";
//        return getExpenses(sql, ps -> {
//            ps.setInt(1, UserID);
//            ps.setString(2,Category);
//        });
//    }

//    public List<Expense> getExpenseByMonth(int UserID,int month , int year) {
//        String sql = "SELECT * FROM expenses WHERE user_id=? AND MONTH(ExpenseDate)=? AND YEAR(ExpenseDate)=?;";
//        return getExpenses(sql, ps -> {
//            ps.setInt(1, UserID);
//            ps.setInt(2,month);
//            ps.setInt(3,year);
//
//        });
//    }

//    public List<Expense> getExpenseByUser(int UserID) {
//        List<Expense> expenses = new ArrayList<>();
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT * FROM expenses WHERE UserID=?;")) {
//            ps.setInt(1, UserID);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Expense expense = new Expense();
//                expense.setExpenseID(rs.getInt("ExpenseID"));
//                expense.setUser_id(rs.getInt("user_id"));
//                expense.setAmount(rs.getFloat("Amount"));
//                expense.setCategory(rs.getString("Category"));
//                expense.setExpenseDescription(rs.getString("ExpenseDescription"));
//                expense.setExpenseDate(LocalDate.parse());
//                expenses.add(expense);
//            }
//        } catch (SQLException e) {
//            System.out.println("Getting Expenses Failed : " + e.getMessage());
//        }
//
//        return expenses;
//    }

    @Override
    public List<Expense> getExpenseByCategory(int user_id, String Category) {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Expense WHERE user_id=? AND Category =?;")) {
            ps.setInt(1, user_id);
            ps.setString(2, Category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseID(rs.getInt("ExpenseID"));
                expense.setUser_id(rs.getInt("user_id"));
                expense.setAmount(rs.getFloat("Amount"));
                expense.setCategory(rs.getString("Category"));
                expense.setExpenseDate(rs.getDate("ExpenseDate").toLocalDate());
                expense.setExpenseDescription(rs.getString("ExpenseDescription"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            System.out.println("Getting Expenses Failed : " + e.getMessage());
        }

        return expenses;
    }

    @Override
    public List<Expense> getExpenseByMonth(int user_id, int month, int year) {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Expense WHERE user_id=? AND  MONTH(ExpenseDate)=? AND YEAR(ExpenseDate)=? ;")) {
            ps.setInt(1, user_id);
            ps.setInt(2, month);
            ps.setInt(3, year);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseID(rs.getInt("ExpenseID"));
                expense.setUser_id(rs.getInt("user_id"));
                expense.setAmount(rs.getFloat("Amount"));
                expense.setCategory(rs.getString("Category"));
                expense.setExpenseDate(rs.getDate("ExpenseDate").toLocalDate());
                expense.setExpenseDescription(rs.getString("ExpenseDescription"));
                expenses.add(expense);
//                PreparedStatement pt=conn.prepareStatement("SELECT SUM(Amount) FROM Expenses WHERE user_id=?;");
//                pt.setFloat(1,user_id);
//
//                ResultSet rse=pt.executeQuery();
//                System.out.println("Total Expense This Month : "+ rs.getFloat("SUM(Amount)"));

            }
        } catch (SQLException e) {
            System.out.println("Getting Expenses Failed : " + e.getMessage());
        }

        return expenses;
    }

    @Override
    public boolean updateExpense(Expense expense) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE Expense SET Category=? , Amount=? , ExpenseDate=? , ExpenseDescription=? WHERE ExpenseID=? ;")) {

            //future --> make it wrap n unwrap (a container box like thing for it )
            if (expense.getExpenseDescription()!= null && expense.getExpenseDescription().length() > 150) {
                System.out.println("Description too long. Only 150 characters allowed !! .");
            } else {
                // Saves the new description
                System.out.println("Description is valid.");
            }

            ps.setString(1, expense.getCategory());
            ps.setFloat(2, expense.getAmount());
            ps.setDate(3, Date.valueOf(expense.getExpenseDate()));
            ps.setString(4, expense.getExpenseDescription());
            ps.setInt(5, expense.getExpenseID());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Oops!! Update Failed : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteExpense(int ExpenseID) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Expense WHERE ExpenseID=? ;")) {

            ps.setInt(1, ExpenseID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Oops!! Delete Failed : " + e.getMessage());
            return false;
        }
    }

    //Retrieving expenses
    private List<Expense> getExpenses (String sql, SQLConsumer<PreparedStatement> parameterSetter) {
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pse = conn.prepareStatement(sql)) {
            parameterSetter.accept(pse);
            ResultSet rs = pse.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("user_id"),
                        rs.getInt("ExpenseID"),
                        rs.getFloat("Amount"),
                        rs.getString("Category"),
                        rs.getDate("ExpenseDate").toLocalDate(),
                        rs.getString("ExpenseDescription")
                );
                expenses.add(expense);
                System.out.println("Total Expenses : " +expenses.size());
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Fetch Failed : " + e.getMessage());
        }
         return expenses;
    }

}
