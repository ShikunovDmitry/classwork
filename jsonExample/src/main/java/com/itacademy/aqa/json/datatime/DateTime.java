package com.itacademy.aqa.json.datatime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class DateTime {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        Event event = new Event("Cource", LocalDateTime.now());

        String jsonString = objectMapper.writeValueAsString(event);
        System.out.println(jsonString);

        Event deserializadEvent = objectMapper.readValue(jsonString, Event.class);

        System.out.println(deserializadEvent.getEventDate() + deserializadEvent.getName());
    }
}
