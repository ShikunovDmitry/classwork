package it.academt.at.sql;

import it.academt.at.utils.ConnectionStringUtil;

import java.sql.*;

public class SqlApp {

  //private static String connectionString="jdbc:sqlite:/Users/Dzmitry_Shykunou/ATCOURCE/classwork/webinar_27_sql/src/main/resources/testDb";

  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("org.sqlite.JDBC Driver Loaded.");
    }catch (ClassNotFoundException e) {
      System.out.println("org.sqlite.JDBC Not Loaded.");
    }

    System.out.println(ConnectionStringUtil.getConnectionString());

    Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
    Statement statement = connection.createStatement();

    String query="SELECT * FROM Employee";

    ResultSet resultSet = statement.executeQuery(query);

    while (resultSet.next()) {
      String firstName = resultSet.getString("firstName");
      String lastName = resultSet.getString("lastName");
      System.out.println(firstName + " " + lastName);
    }

    resultSet.close();
    statement.close();
    connection.close();



  }
}
