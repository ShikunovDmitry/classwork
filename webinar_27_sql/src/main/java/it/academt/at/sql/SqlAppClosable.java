package it.academt.at.sql;

import it.academt.at.utils.ConnectionStringUtil;

import java.sql.*;

public class SqlAppClosable {

  //private static String connectionString="jdbc:sqlite:/Users/Dzmitry_Shykunou/ATCOURCE/classwork/webinar_27_sql/src/main/resources/testDb";

  public static void main(String[] args) throws ClassNotFoundException, SQLException {

    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("org.sqlite.JDBC Driver Loaded.");
    }catch (ClassNotFoundException e) {
      System.out.println("org.sqlite.JDBC Not Loaded.");
    }

    System.out.println(ConnectionStringUtil.getConnectionString());

    try( Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
         Statement statement = connection.createStatement()) {

      String query = "SELECT * FROM Employee";

      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        System.out.println(firstName + " " + lastName);
      }

      String ageQuery = "SELECT DISTINCT FirstName, LastName,\n" +
          "ROUND((JULIANDAY(Date('now')) - JULIANDAY(Date(pd.DateOfBirth)))/365) as age\n" +
          "FROM Employee e \n" +
          "JOIN PersonalData pd ON pd.EmployeeId = e.EmployeeID\n" +
          "JOIN Department d on e.EmployeeID =d.DepartmentID\n" +
          "JOIN EmployeeSkill es on es.EmployeeId = e.EmployeeID\n" +
          "JOIN Skills s on s.skillId = es.SkillId ";

      ResultSet ageResultSet = statement.executeQuery(ageQuery);
      while (ageResultSet.next()) {
        String firstName = ageResultSet.getString(1);
        String lastName = ageResultSet.getString(2);
        Integer age = ageResultSet.getInt("age");
        System.out.println(firstName + " " + lastName + " " + age);
      }

    }catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
