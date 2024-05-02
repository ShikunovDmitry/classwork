package com.itacademy.aqa.jdbc;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Dzmitry_Shykunou\\Downloads\\SQLiteDatabaseBrowserPortable\\Employees.db";

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No sql lite driver");
            return;
        }
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);

            statement = connection.createStatement();

            String query = "SELECT DISTINCT FirstName, LastName, DepartmentName, SkillName, SkillName as skill\n" +
                    "from Employee e\n" +
                    "LEFT JOIN Department d on e.DepartmentId = d.DepartmentId\n" +
                    "LEFT JOIN PersonalData pd on pd.EmployeeId = e.EmployeeId\n" +
                    "LEFT JOIN EmployeeSkill es on e.EmployeeId = es.EmployeeId\n" +
                    "LEFT JOIN Skills s on s.SkillId = es.SkillId;";

            String inserQuery = "INSERT INTO Employee( FirstName, LastName) values('Dmitry', 'Sh');";
            statement.execute(inserQuery);

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("First Name: " + resultSet.getString("FirstName")
                + " Last Name: " + resultSet.getString("LastName")
                + " Department Name: " + resultSet.getString("DepartmentName")
                + " Skill Name: " + resultSet.getString("skill"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close connection/statement");
            }
        }
    }
}
