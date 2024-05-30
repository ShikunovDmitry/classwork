package com.itacademy.aqa.json.list;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Dmitry",67));
        employees.add(new Employee("Vasya",44));

        Department department = new Department("IT", employees);

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(department);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonString);

        Department desearializeDepartment = null;
        try {
            desearializeDepartment = objectMapper.readValue(jsonString, Department.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(desearializeDepartment);
    }
}
