package it.academt.at.dto;

public class EmployeeDto {
  private String firstName;
  private String lastName;
  private String departmentName;
  private Integer age;

  public EmployeeDto(String firstName, String lastName, String departmentName, Integer age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.departmentName = departmentName;
    this.age = age;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
