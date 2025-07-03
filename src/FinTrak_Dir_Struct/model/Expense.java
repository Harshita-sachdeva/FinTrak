package FinTrak_Dir_Struct.model;

import java.time.LocalDate;

public class Expense {
    private int user_id;
    private int ExpenseID;
    private float Amount;
    private String Category;
    private String ExpenseDescription;
    private LocalDate ExpenseDate;

    public Expense(){}

    //Constructor for FETCHING Data from DB
    public Expense(int user_id,int expenseID, float amount, String category , LocalDate expenseDate ,String expenseDescription) {
        this.user_id=user_id;
        this.ExpenseID = expenseID;
        this.Amount = amount;
        this.Category = category;
        this.ExpenseDescription = expenseDescription;
        this.ExpenseDate = expenseDate;
    }

    //Constructor for INSERTING Data into DB
    public Expense(int user_id,String category,float amount,LocalDate expenseDate,String expenseDescription) {
        this.user_id=user_id;
        this.Amount = amount;
        this.Category = category;
        this.ExpenseDescription = expenseDescription;
        this.ExpenseDate = expenseDate;
    }



    //For the updateExpense function in MenuUI
    public Expense(int expenseID, float amt, String cat, LocalDate ExpenseDate, String des) {
        this.ExpenseID=expenseID;
        this.Amount=amt;
        this.Category=cat;
        this.ExpenseDate=ExpenseDate;
        this.ExpenseDescription=des;
    }

    //Getters and Setters
    public int getExpenseID() {
        return ExpenseID;
    }

    public void setExpenseID(int expenseID) {
        ExpenseID = expenseID;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getExpenseDescription() {
        return ExpenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        ExpenseDescription = expenseDescription;
    }

    public LocalDate getExpenseDate() {
        return ExpenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        ExpenseDate = expenseDate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
