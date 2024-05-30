package com.itacademy.aqa.json.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;


public class Deserialize {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> personData = new HashMap<>();
        personData.put("firstName", "John");
        personData.put("lastName", "Lenon");
        personData.put("age", 30);

        try {
            String jsonPerson = mapper.writeValueAsString(personData);
            System.out.println(jsonPerson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
