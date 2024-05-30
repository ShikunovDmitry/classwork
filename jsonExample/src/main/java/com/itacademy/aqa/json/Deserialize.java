package com.itacademy.aqa.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itacademy.aqa.json.map.Person;

import java.util.HashMap;
import java.util.Map;


public class Deserialize {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
//         Настройки сериализации
        mapper.enable(SerializationFeature.INDENT_OUTPUT);


        com.itacademy.aqa.json.map.Person person = new Person("John", "Doe", 40);

        try {
            String jsonPerson = mapper.writeValueAsString(person);
            System.out.println(jsonPerson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
