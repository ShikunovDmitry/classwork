package com.itacademy.aqa.jdbc;

public class Employee {
    private String firsName;
    private String lastName;
    private String departmentName;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirsName() {
        return firsName;
    }

    private Integer departmentId;

    public Employee(String firsName, String lastName, String departmentName) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
    }
    public Employee(String firsName, String lastName, Integer departmentId) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return firsName + "|" + lastName + "|" + departmentName;
    }
}
