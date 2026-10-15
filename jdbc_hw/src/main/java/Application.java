import db.QueryExecutor;
import pojo.Account;
import pojo.Transaction;
import pojo.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Application {

    private static Scanner scanner;

    private static QueryExecutor dbExecutor;

    public static void main(String[] args) {
        if (!init()) {
            return;
        }
        String actionCode;
        do {
            printMenu();
            actionCode = scanner.nextLine();
            try {
                switch (actionCode) {
                    case "1":
                        User newUser = getUser();
                        dbExecutor.addUser(newUser);
                        break;
                    case "2":
                        Account newAccount = getAccount();
                        dbExecutor.addAccount(newAccount);
                        break;
                    case "3":
                        Transaction newDeposit = getTransaction();
                        dbExecutor.addTransaction(newDeposit);
                        System.out.println("Current balance:"+dbExecutor.getCurrentBalance(newDeposit.getAccountId()));
                        break;
                    case "4":
                        Transaction newWithdraw = getTransaction();
                        newWithdraw.setAmount(-newWithdraw.getAmount());
                        dbExecutor.addTransaction(newWithdraw);
                        System.out.println("Current balance:"+dbExecutor.getCurrentBalance(newWithdraw.getAccountId()));
                        break;
                    case "5":
                        System.out.println("Thanks for using the program!");
                        break;
                    default:
                        System.out.println("Unknown option. Please enter again");
                }
            } catch (SQLException ex) {
                System.out.println("Db violation " + ex.getMessage());
            }
        } while (!"5".equals(actionCode));
        shutDown();
    }

    public static boolean init() {
        if (!driverChecker()) {
            System.out.println("No sqlite driver");
            return false;
        }
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/project.properties"));
            dbExecutor = new QueryExecutor("jdbc:sqlite:" + properties.getProperty("DB_PATH"));
            System.setProperties(properties);
        } catch (SQLException ex) {
            System.out.println("Can't connect to database");
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("Can't find property file");
            return false;
        } catch (IOException e) {
            System.out.println("Can't read property file");
            return false;
        }
        scanner = new Scanner(System.in);
        return true;
    }

    public static void shutDown() {
        scanner.close();
        try {
            dbExecutor.closeDb();
        } catch (SQLException ex) {
            System.out.println("Can't close db");
        }
    }

    public static User getUser() {
        User newUser = new User();
        boolean valid;
        do {
            try {
                valid = true;
                System.out.println("enter an id");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("enter the name");
                String name = scanner.next();
                if (name.length() > 50) {
                    throw new RuntimeException("too long name");
                }
                scanner.nextLine();
                System.out.println("enter user address or leave blank");
                String address = scanner.nextLine();
                if (address.length() > 255) {
                    throw new RuntimeException("too long address");
                }
                newUser.setUserId(id);
                newUser.setName(name);
                newUser.setAddress(address);
            } catch (Exception e) {
                System.out.println(String.format("Invalid data. %s. Try again", e.getMessage()));
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                valid = false;
            }
        } while (!valid);
        return newUser;
    }

    public static Account getAccount() {
        Account newAccount = new Account();
        boolean valid;
        do {
            try {
                valid = true;
                System.out.println("enter accountId id");
                int id = scanner.nextInt();
                System.out.println("enter userId for this account");
                int userId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("enter the currency");
                String currency = scanner.nextLine();
                if (currency.length() > 10) {
                    throw new RuntimeException("too long currency name");
                }
                newAccount.setAccountId(id);
                newAccount.setUserId(userId);
                newAccount.setCurrency(currency);
                newAccount.setBalance(0);
            } catch (Exception e) {
                System.out.println(String.format("Invalid data. %s. Try again", e.getMessage()));
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                valid = false;
            }
        } while (!valid);
        return newAccount;
    }

    public static Transaction getTransaction() {
        Transaction newTransaction = new Transaction();
        boolean valid;
        do {
            try {
                valid = true;
                System.out.println("enter transaction id");
                int id = scanner.nextInt();
                System.out.println("enter accountId for this transaction");
                int accountId = scanner.nextInt();
                System.out.println("enter amount");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount <= 0) {
                    throw new RuntimeException("Amount should be positive");
                }
                if (amount > 100000000) {
                    throw new RuntimeException("Amount should be less than 100000000");
                }
                newTransaction.setTransactionId(id);
                newTransaction.setAccountId(accountId);
                newTransaction.setAmount(amount);
            } catch (Exception e) {
                System.out.println(String.format("Invalid data. %s. Try again", e.getMessage()));
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                valid = false;
            }
        } while (!valid);
        return newTransaction;
    }

    public static void printMenu() {
        System.out.println("Please select an action");
        System.out.println("1 - new user");
        System.out.println("2 - new account");
        System.out.println("3 - deposit");
        System.out.println("4 - withdraw");
        System.out.println("5 - quit");
    }

    public static boolean driverChecker() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
