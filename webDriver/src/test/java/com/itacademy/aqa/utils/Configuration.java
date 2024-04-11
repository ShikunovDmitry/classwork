package com.itacademy.aqa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    // mvn clean test -Denvironment=dev
    //System.getProperty("environment")
    private static Properties properties;
    public static Properties getProperties(){
        if(properties == null){
            try {
                properties = new Properties();
                //initialize file based on environment we need
                properties.load(new FileInputStream("project.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return  properties;
    }
}
