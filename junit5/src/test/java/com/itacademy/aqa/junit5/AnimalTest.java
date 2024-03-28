package com.itacademy.aqa.junit5;

import com.itacademy.aqa.junit5.Animal;
import com.itacademy.aqa.junit5.Cat;
import com.itacademy.aqa.junit5.Dog;
import com.itacademy.aqa.junit5.Pig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AnimalTest {
    Cat cat;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("all test init");
    }
    @BeforeEach
    public void init(){
        System.out.println("Init cat");
        cat = new Cat();
    }

    @Test
    public void testDog(){
        System.out.println("run first test");
        Cat cat = new Cat();
        Assertions.assertEquals("Cat", cat.whoIam(),"Cat is not cat");
    }
    @Test
    public void testCat(){
        System.out.println("run second test");
        Cat cat = new Cat();
        Assertions.assertNotEquals("Dog", cat.whoIam(),"Cat is dog");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("delete cat");
        cat = null;
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("all cleanup");
    }
    @Test
    public void exceptionTest(){
        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> {
            new Cat().whoIam();
        });
        Assertions.assertNotNull(exception.getMessage());
    }

    public static List<Object[]> params() {
        List<Object[]> results = new ArrayList<>();
        results.add(new Object[] {new Dog(), "Dog"});
        results.add(new Object[] {new Cat(), "Cat"});
        results.add(new Object[] {new Pig(), "Pig"});
        return results;
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testWithParams(Animal animal, String name) {
        Assertions.assertEquals(name, animal.whoIam());
    }
}
