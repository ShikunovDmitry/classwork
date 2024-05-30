package com.itacademy.aqa.json.collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;

public class SerializeExample {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "[{\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":40},{\"firstName\":\"John\",\"lastName\":\"Smith\",\"age\":60}]";

        try {
            List<Person> persons = objectMapper.readValue(jsonString,
                    new TypeReference<List<Person>>(){});
            persons.forEach(person -> {
                System.out.println(person.getFirstName());
                System.out.println(person.getLastName());
                System.out.println(person.getAge());
            });
            Person[] people = objectMapper.readValue(jsonString,Person[].class);
            for(Person person: people){
                System.out.println(person.getFirstName());
                System.out.println(person.getLastName());
                System.out.println(person.getAge());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
