# FinTrak - Personal Expense Tracker (Documentation) 

## Project Overview 
FinTrak is a command-line based expense tracking application built in java with MySQl for database storage .It helps the users to record and analyse their daily expenses.

## Purpose
FinTrak was built to help users manage , analyse as well as control their expenses using the category and monthly reporting feature ,allowing them to efficiently manage their finances and upgrade themselves financially .Easy to use for everyone .

## Features 
- User authentication (register user , login user)  
- Add new expenses( amount , category , date , descrption )
- View all past expenses made by a particular user
- Update or delete existing entries
- Fiter expenses by category or month
- Total expense summary

## Technologies Used 
- Java(Core + JDBC)
- MySQL(Database)
- Github(Version Control)
- Intellij (IDE)
- Model-View-Controller (Software architecture)

## System Requirements
- JDK 17+
- MySQL Workbench + Server  installed
- MySqL Connector JAR File
- Command-line terminal
 
## Setup Instructions
1.Clone the repository :
  git clone  https://github.com/Harshita-sachdeva/FinTrak.git

2.Import project in your Java IDE.

3.Create a MySQL database :
  CREATE DATABSE FinTrakDB;
  
4.Run the SQL script provided in `/db` folder to create the `expense` table.

5.Update your MySQL username & password in the `DBConnection.java` file.

6.Compile and run `Main.java`.


## Directory Structure:
- Main                 // For interacting with the user
- MenuUI               // For handling the user interaction n dashboards functionality also
- Model package        // For data getting(user , expenses)
- Service layer        // For creating a layer between the UI n DAO LAYER , includes bussiness                              operations
- Dao layer           // For internal operations and logics of the application's features
- DBConnection.java    //DB utility for connection handling

## Future Improvements:
- Adding GUI to make it more interactive and engaging as well.
- AI Suggestions for avoiding miscellaneous expenses.
- Integrating a chatbot to have conversation over expenses made .  
- Creating reports of the expenses made in a month.
- Exporting reports as PDF and CSV.

## Author :
Harshita Sachdeva
 




  
