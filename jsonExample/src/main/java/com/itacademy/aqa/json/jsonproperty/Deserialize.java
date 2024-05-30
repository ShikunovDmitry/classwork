package com.itacademy.aqa.json.jsonproperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Deserialize {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Person person = new Person("John", "Doe", 40);

        try {
            String jsonPerson = mapper.writeValueAsString(person);
            System.out.println(jsonPerson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
