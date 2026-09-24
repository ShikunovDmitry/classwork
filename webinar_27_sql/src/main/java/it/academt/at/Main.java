package it.academt.at;

import it.academt.at.dto.EmployeeDto;
import it.academt.at.sql.SqlUtil;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    EmployeeDto employeeDto = new EmployeeDto("John", "Lenon", "Sales",null);
    SqlUtil.createEmployee(employeeDto);
    SqlUtil.createAccountsTableIfNotExists();

  }
}
