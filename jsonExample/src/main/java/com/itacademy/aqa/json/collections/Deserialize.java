package com.itacademy.aqa.json.collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;


public class Deserialize {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        List<Person> people = Arrays.asList(
                new Person("John", "Doe", 40),
                new Person("John", "Smith", 60));

        try {
            String jsonPeoples = mapper.writeValueAsString(people);
            System.out.println(jsonPeoples);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
