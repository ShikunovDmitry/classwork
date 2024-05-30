package com.itacademy.aqa.json.jsoncreator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"first_name\":\"John\",\"last_name\":\"Doe\",\"age\":30}";

        try {
            Person person = objectMapper.readValue(jsonString, Person.class);
            System.out.println(person.getFirstName());
            System.out.println(person.getLastName());
            System.out.println(person.getAge());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
