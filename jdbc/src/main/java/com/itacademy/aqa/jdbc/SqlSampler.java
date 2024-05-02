package com.itacademy.aqa.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class SqlSampler {
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Dzmitry_Shykunou\\Downloads\\SQLiteDatabaseBrowserPortable\\Employees.db";
    public static final String ADD_EMPLOYEE_QUERY = "INSERT INTO Employee(FirstName, LastName, DepartmentId) values(?, ?, ?);";

    public static final String GET_MAX_USER_ID = "SELECT MAX(EmployeeId) as max from Employee";

    public static void main(String[] args) {
        List<Employee> employeeList = getAllEmployee();

        employeeList.stream().forEach(System.out::println);

        Employee employee = new Employee("John", "Doe",3);
        insertEmployee(employee);

        employeeList = getAllEmployeeInDepartment("Production");
        System.out.println("Production only: ");
        employeeList.stream().forEach(System.out::println);
        System.out.println(getMaxEmployeeId());

    }

    public static List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sql lite driver");
            return null;
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()){

            String query = "SELECT DISTINCT FirstName, LastName, DepartmentName, SkillName, SkillName as skill\n" +
                    "from Employee e\n" +
                    "LEFT JOIN Department d on e.DepartmentId = d.DepartmentId\n" +
                    "LEFT JOIN PersonalData pd on pd.EmployeeId = e.EmployeeId\n" +
                    "LEFT JOIN EmployeeSkill es on e.EmployeeId = es.EmployeeId\n" +
                    "LEFT JOIN Skills s on s.SkillId = es.SkillId;";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                employees.add(new Employee(resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("DepartmentName")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public static List<Employee> getAllEmployeeInDepartment(String departmentName){
        List<Employee> employees = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sql lite driver");
            return null;
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()){

            String query = "SELECT DISTINCT FirstName, LastName, DepartmentName, SkillName, SkillName as skill\n" +
                    "from Employee e\n" +
                    "LEFT JOIN Department d on e.DepartmentId = d.DepartmentId\n" +
                    "LEFT JOIN PersonalData pd on pd.EmployeeId = e.EmployeeId\n" +
                    "LEFT JOIN EmployeeSkill es on e.EmployeeId = es.EmployeeId\n" +
                    "LEFT JOIN Skills s on s.SkillId = es.SkillId " +
                    "WHERE d.DepartmentName='%s';";

            ResultSet resultSet = statement.executeQuery(String.format(query,departmentName));
            while (resultSet.next()){
                employees.add(new Employee(resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("DepartmentName")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public static void insertEmployee(Employee employee){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sql lite driver");
            return;
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE_QUERY)){
            preparedStatement.setString(1, employee.getFirsName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getDepartmentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Integer getMaxEmployeeId(){
        List<Employee> employees = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sql lite driver");
            return null;
        }

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(GET_MAX_USER_ID);
            return resultSet.getInt("max");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
