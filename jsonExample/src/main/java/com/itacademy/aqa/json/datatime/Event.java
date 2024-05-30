package com.itacademy.aqa.json.datatime;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Event {
    private String name;

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event(String name, LocalDateTime eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
    public Event() {
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime eventDate;


}
