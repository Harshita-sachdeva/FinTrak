package FinTrak_Dir_Struct.FinTrakUI;

import FinTrak_Dir_Struct.model.Expense;
import FinTrak_Dir_Struct.model.User;
import FinTrak_Dir_Struct.service.ExpenseService;
import FinTrak_Dir_Struct.service.UserService;
import FinTrak_Dir_Struct.serviceImpl.ExpenseServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuUI {
    /// final to prevent unauthorized access to internal logic
    private final UserService userService;
    private final ExpenseService expenseService = new ExpenseServiceImpl();
    private final Scanner sc = new Scanner(System.in);           //made final avoid external classes to access or close Scanner acci.
    public User loggedInUser = null;                            //Initialising to null(no user yet logged in )

    /// not final as logging status will eventually change often

    //Constructor to get object of userservice in current class (methods here can easily access)
    public MenuUI(UserService userService) {
        this.userService = userService;
    }

//    public void gobackhome(){
//        homeMenu();
//    }
    //Home menu
    public void homeMenu(){
        while(true){
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose : ");
            int choice=Integer.parseInt(sc.nextLine()); //to avoid newline bugs

            switch(choice){
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("\nThanks for using FinTrak 💸 — Stay on top of your spending!");
                    System.out.println("App exited successfully. 👋");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid Choice");
            }

            if(loggedInUser!=null){
                dashboard();
            }
        }
    }


    public void register() {
        System.out.print("Username : ");
        String uname = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();

        if (userService.registerUser(uname, pass)) {
            System.out.println("✅ Registered! ");
        } else {
            System.out.println("❌ Failed (maybe user already exists?)"); //for checking whether the user is already registered or not..if not registred now
        }
    }

    public void login(){
        System.out.print("Username : ");
        String uname = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();

        User u=userService.loginUser(uname,pass); //for checking user is logged in or not
        if(u!=null){
            loggedInUser=u;
            System.out.println("✅ Logged in ! ");
        }
        else{
            System.out.println("❌ Invalid Credentials ");
        }
    }

    public void dashboard(){
       while(true){
           System.out.println("\n== FinTrak Dashboard ==");
           System.out.println("1. Add Expense");
           System.out.println("2. View All ");
           System.out.println("3. View By Category ");
           System.out.println("4. View By Month");
           System.out.println("5. Update Expense");
           System.out.println("6. Delete Expense");
           System.out.println("7. Logout");
           System.out.println("8. Go Back to Home ");
           System.out.println("Choose: ");
           int choice=Integer.parseInt(sc.nextLine());

           switch(choice){
               case 1:
                   addExpense();
                   break;
               case 2:
                   print(expenseService.getExpenses(loggedInUser.getUserID()));
                   break;
               case 3:
                   viewByCategory();
                   break;
               case 4:
                   viewByMonth();
                   break;
               case 5:
                   updateExpense(expenseService.getExpenses(loggedInUser.getUserID()));
                   break;
               case 6:
                   deleteExpense(expenseService.getExpenses(loggedInUser.getUserID()));
                   break;
               case 7:
                   loggedInUser=null;
                   System.out.println("✅ YOU ARE LOGGED OUT ! ");
                   return;
               case 8:
                   homeMenu();
                   break;
               default:
                   System.out.println("Invalid Option");
           }
       }

    }
    private void addExpense(){
        System.out.print("Category : ");
        String cat=sc.nextLine();
        System.out.print("Amount : ");
        float amt=Float.parseFloat(sc.nextLine());
        System.out.print("Date (yyyy-mm-dd) : ");
        LocalDate date= LocalDate.parse(sc.nextLine());
        System.out.print("Expense Description : ");
        String des=sc.nextLine();

        Expense e = new Expense(loggedInUser.getUserID(), cat, amt, date, des);
        if(expenseService.addExpense(e)){
            System.out.println("✅ Expense added ");
        }
        else{
            System.out.println("❌ Failed to add expense");
        }

    }

    private void viewByMonth(){
        System.out.print("Enter the month (1-12) : ");
        int month=Integer.parseInt(sc.nextLine());
        System.out.print("Enter year (e.g., 2025) : ");
        int year=Integer.parseInt(sc.nextLine());
        print(expenseService.getExpenseByMonth(loggedInUser.getUserID(), month,year));

    }
    private void viewByCategory(){
        System.out.print("Enter Category :  ");
        String cat=sc.nextLine();
        print(expenseService.getExpensesByCategory(loggedInUser.getUserID(), cat));
    }
    private void updateExpense(List<Expense> expenses){
        if(expenses.isEmpty()){
            System.out.println("❗No Expenses Found : Can't Update (Add expense first)");
            return;
        }
        System.out.print("Your Current Expenses : ");
        print(expenseService.getExpenses(loggedInUser.getUserID()));
        System.out.print("Expense ID to update : ");
        int id=Integer.parseInt(sc.nextLine());
        System.out.print("New Category : ");
        String cat=sc.nextLine();
        System.out.print("New Amount : ");
        int amt=Integer.parseInt(sc.nextLine());
        System.out.print("New Date (yyyy-mm-dd) : ");
        LocalDate date=LocalDate.parse(sc.nextLine());
        System.out.print("New description : ");
        String des=sc.nextLine();

        Expense e=new Expense(id,amt,cat,date,des);
        if(expenseService.updateExpense(e)){
            System.out.println("✅ Updated Successfully ");
        }
        else{
            System.out.println("❌ Update Failed ");
        }
    }

    //on making it static expenseService object wouldn't accessible (static methods cob
    private void deleteExpense(List<Expense> expenses){
        if(expenses.isEmpty()){
            System.out.println("❗No Expenses Found : Can't Delete (Add expense first)");
            return;
        }
        print(expenseService.getExpenses(loggedInUser.getUserID()));
        System.out.print("Expense ID to delete : ");
        int expenseId=Integer.parseInt(sc.nextLine());
        if(expenseService.deleteExpense(expenseId)){
            System.out.println("✅ Expense Deleted ");
        }
        else{
            System.out.println("❌ Delete Failed ");
        }
    }

    //Separate print function for formatting the printing as i want
    private void print(List<Expense> expenses){
        //if no expenses stored yet
        if(expenses.isEmpty()){
            System.out.println("❗No Expenses Found ");
            return;
        }
        //for table headers
//        String row=String.format("%-8s %-15s %-12s %-15s %-11s %-3s\n","Id","Expense Id","Amount","Category","Date","Description");
//        System.out.println(row.replace(' ',' '));
        System.out.printf("%-8s %-15s %-12s %-15s %-17s %-3s\n","Id","Expense Id","Amount","Category","Date","Description");
        //System.out.printf("%-8s %-15s %-17s %-16s %-10s %-1s\n","Id","ExpenseId","Amount","Category","Date","Description");
        for(Expense e:expenses){
            System.out.printf("%-11s %-12s %-13s %-12s %-14s %-9s\n",e.getUser_id(),e.getExpenseID(),e.getAmount(),e.getCategory(),e.getExpenseDate(),e.getExpenseDescription());
        }

    }
//    private static void updatedash(){
//        while(true){
//            System.out.println("== What do you want to update == ");
//
//        }
//    }


}
