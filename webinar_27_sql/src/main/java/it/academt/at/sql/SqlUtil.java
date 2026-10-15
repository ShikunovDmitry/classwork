package it.academt.at.sql;

import it.academt.at.dto.EmployeeDto;
import it.academt.at.utils.ConnectionStringUtil;

import java.sql.*;

public class SqlUtil {
  private static final String addNewEmployeeQuery = "INSERT INTO employee(EmployeeID, FirstName, LastName, DepartmentId) VALUES (?, ?, ?, ?)";

  public static void createEmployee(EmployeeDto employeeDto) {

    try (Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
         PreparedStatement statement = connection.prepareStatement(addNewEmployeeQuery)) {
      Integer employeeId = getNextEmployeeId();
      Integer departmentId = getDepartmentIdByName(employeeDto.getDepartmentName());
      statement.setInt(1, employeeId);
      statement.setString(2, employeeDto.getFirstName());
      statement.setString(3, employeeDto.getLastName());
      statement.setInt(4, departmentId);
      int updated = statement.executeUpdate();
      System.out.println("Updated: " + updated);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static Integer getDepartmentIdByName(String departmentName) throws SQLException {
    String query = "SELECT DepartmentId FROM Department WHERE DepartmentName = ?";
    try (Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
         PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, departmentName);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return resultSet.getInt("DepartmentId");
      } else {
        throw new SQLException(departmentName + " not found");
      }
    } catch (SQLException e) {
      throw e;
    }
  }

  private static Integer getNextEmployeeId() throws SQLException {
    String query = "SELECT MAX(EmployeeID) as max FROM Employee";
    try (Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
         Statement statement = connection.createStatement()) {

      ResultSet resultSet = statement.executeQuery(query);

      if (resultSet.next()) {
        return resultSet.getInt("max") + 1;
      } else {
        return 1;
      }
    } catch (SQLException e) {
      throw e;
    }

  }

  public static void createAccountsTableIfNotExists() throws SQLException {
    String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "username TEXT NOT NULL, " +
        "email TEXT NOT NULL, " +
        "is_active INTEGER)";
    try (Connection connection = DriverManager.getConnection(ConnectionStringUtil.getConnectionString());
         Statement statement = connection.createStatement()) {
      statement.execute(sql);

    } catch (SQLException e) {
      throw e;
    }

  }
}
