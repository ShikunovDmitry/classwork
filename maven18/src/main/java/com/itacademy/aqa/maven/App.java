package com.itacademy.aqa.maven;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        System.out.println("Source words");

        for (int i = 0; i < 10; i++) {
            words.add(RandomStringUtils.randomAlphabetic(10));
            System.out.println(words.get(i));
        }
        System.out.println("Transformed words");

        for (int i = 0; i < 10; i++) {
            System.out.println(StringUtils.capitalize(words.get(i).toLowerCase()));
        }
    }
}
