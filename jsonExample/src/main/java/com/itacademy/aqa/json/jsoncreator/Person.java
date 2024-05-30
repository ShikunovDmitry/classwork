package com.itacademy.aqa.json.jsoncreator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person() {}

    @JsonCreator
    public Person(@JsonProperty ("first_name") String firstName,
                  @JsonProperty ("last_name") String lastName,
                  @JsonProperty ("age") int age) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
