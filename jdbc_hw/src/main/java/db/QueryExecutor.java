package db;

import pojo.Account;
import pojo.Transaction;
import pojo.User;

import java.sql.*;

public class QueryExecutor {

    private Connection dbCon;

    public QueryExecutor(String connectionString) throws SQLException {
        dbCon = DriverManager.getConnection(connectionString);
        try(Statement initStatement = dbCon.createStatement()) {
            initStatement.executeUpdate("PRAGMA foreign_keys = ON");
        }
    }

    public void closeDb() throws SQLException {
        dbCon.close();
    }

    public void addUser(User newUser) throws SQLException {
        try(Statement statement = dbCon.createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO Users VALUES(%d, '%s', '%s')",
                    newUser.getUserId(), newUser.getName(), newUser.getAddress()));
        }


    }

    public void addAccount(Account newAccount) throws SQLException {
        try(Statement statement = dbCon.createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO Accounts VALUES(%d, %d, '%s', %.3f)",
                    newAccount.getAccountId(), newAccount.getUserId(), newAccount.getCurrency(), newAccount.getBalance()));
        }
    }

    public void addTransaction(Transaction newTransaction) throws SQLException {
		if (getCurrentBalance(newTransaction.getAccountId())+ newTransaction.getAmount() > 2000000000) {
			throw new SQLException("Can't process transaction. The limit is exceeded");
		}
        try(Statement statement = dbCon.createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO Transactions VALUES(%d, %d, %.3f)",
                    newTransaction.getTransactionId(), newTransaction.getAccountId(), newTransaction.getAmount()));
            if (newTransaction.getAmount() > 0) {
                statement.executeUpdate(String.format("UPDATE Accounts SET balance=balance+%.3f WHERE accountId=%d", newTransaction.getAmount(), newTransaction.getAccountId()));
            } else {
                statement.executeUpdate(String.format("UPDATE Accounts SET balance=balance-%.3f WHERE accountId=%d", -newTransaction.getAmount(), newTransaction.getAccountId()));
            }
        }
    }

    public double getCurrentBalance(int accountId) throws SQLException {
        try(Statement statement = dbCon.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT balance FROM Accounts WHERE accountId=" + accountId);
            double balance = rs.getDouble("balance");
            return balance;
        }

    }

    public int maxValue(String tableName, String fieldName) throws SQLException {
        try(Statement statement = dbCon.createStatement()) {
            ResultSet rs = statement.executeQuery(String.format("SELECT max(%s) FROM %s", fieldName, tableName));
            int max = rs.getInt(1);
            statement.close();
            return max;
        }
    }

}
