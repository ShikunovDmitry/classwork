package com.itacademy.aqa.onliner;

public enum TopMenuEnum {
    CATALOG("Каталог"),
    NEWS("Нововсти");

    public String getValue() {
        return value;
    }

    private final String value;

    TopMenuEnum(String value){
        this.value = value;
    }
}
