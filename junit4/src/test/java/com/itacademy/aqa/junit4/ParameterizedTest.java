package com.itacademy.aqa.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    private String expectedAnimalName;
    private Animal animal;

    public ParameterizedTest(Animal animal, String expectedAnimalName){
        this.expectedAnimalName = expectedAnimalName;
        this.animal = animal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getAnimals(){
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new Dog(),"Dog"});
        params.add(new Object[]{new Cat(),"Cat"});
        params.add(new Object[]{new Pig(),"Pig"});
        return params;
    }

    @Test
    public void testAnimal(){
        System.out.println(" Verify that " + animal.getClass().getSimpleName() + " is " + expectedAnimalName);
        Assert.assertEquals(animal.getClass().getName() + " is not " + expectedAnimalName,
                expectedAnimalName, animal.whoIam());
    }


}
