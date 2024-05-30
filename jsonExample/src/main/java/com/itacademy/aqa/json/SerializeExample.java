package com.itacademy.aqa.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itacademy.aqa.json.map.Person;

public class SerializeExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Настройки десериализации
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        String jsonString = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30,\"height\":180}";

        String jsonString = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30}";

        try {
            com.itacademy.aqa.json.map.Person person = objectMapper.readValue(jsonString, Person.class);
            System.out.println(person.getFirstName());
            System.out.println(person.getLastName());
            System.out.println(person.getAge());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
