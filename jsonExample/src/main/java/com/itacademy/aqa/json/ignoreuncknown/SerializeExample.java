package com.itacademy.aqa.json.ignoreuncknown;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30,\"height\":183}";

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
