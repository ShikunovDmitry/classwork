package com.itacademy.aqa.webdriver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RequestBody {
    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    @JsonProperty(value = "Hello")
    private String hello;
}
