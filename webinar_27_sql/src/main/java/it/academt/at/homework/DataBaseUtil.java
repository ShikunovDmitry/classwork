package it.academt.at.homework;

//for create table Accounts
//create TABLE IF NOT EXISTS Accounts(
//accountId int(10) NOT NULL,
//userId int(10) NOT NULL,
//currency varchar(10) NOT NULL,
//balance decimal(15,3) CHECK (balance<=2000000000),
//PRIMARY KEY (userId, currency)
//FOREIGN KEY(userId) REFERENCES Users(userId)

//for create table Accounts
//create TABLE IF NOT EXISTS Accounts(
//accountId int(10) PRIMARY KEY,
//userId int(10) NOT NULL,
//currency varchar(10) NOT NULL,
//balance decimal(15,3) CHECK (balance<=2000000000),
//FOREIGN KEY(userId) REFERENCES Users(userId)
// if(SELECT * FROM Accounts where userId = <userId> and currency = <currency>){
// throw Exception(account in currency exist
//else{add New account}

//create TABLE IF NOT EXISTS users(
//	userId int(10) PRIMARY KEY,
//	name varchar(50),
//	address varchar(255)
//);

public class DataBaseUtil {

  public void addNewUser(User user){

  }

  public void addAccount(Account account){

  }

  public void addTransaction(Transaction transaction){

    String accountId = "";
    Double amount = 0.0;
    updateBalance(accountId,amount);
  }

  private void updateBalance(String accountId, Double amount) {
    String query = String.format("UPDATE Accounts SET balance = balance+%.3f WHERE accountId = %s", amount, accountId);
  }


  public double getBalance(int accountId){
    return 0;
  }
}
