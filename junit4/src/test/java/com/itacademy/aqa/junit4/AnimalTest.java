package com.itacademy.aqa.junit4;

import org.junit.*;


public class AnimalTest {
    Cat cat;

    @BeforeClass
    public static void beforeAll(){
        System.out.println("all test init");
    }
    @Before
    public void init(){
        System.out.println("Init cat");
        cat = new Cat();
    }

    @Test
    public void testCatCanNameItself(){
        System.out.println("run first test");
        Assert.assertEquals("Cat is not cat","Cat", cat.whoIam());
    }
    @Test
    public void testCatIsNotDog(){
        System.out.println("run second test");
        Assert.assertNotEquals("Cat is dog","Dog", cat.whoIam());
    }

    @After
    public void tearDown(){
        System.out.println("delete cat");
        cat = null;
    }

    @AfterClass
    public static void afterAll(){
        System.out.println("all cleanup");
    }
}
