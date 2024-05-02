package com.itacademy.aqa.cucumber.stepdefs;

import com.itacademy.aqa.cucumber.Menu;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ExampleStepdefs {
    List<String> source;
    Map<String, String> sourceMap;
    Map<String, Integer> sourceIntegerMap;
    Map<String, Boolean> sourceBooleanMap;

    @DataTableType
    public Menu menuItemTransformer(Map<String, String> row){
        Menu menu = new Menu();
        menu.setTitle(row.get("title"));
        menu.setAvailable(Boolean.parseBoolean(row.get("isAvailable")));
        menu.setSubMenuCount(Integer.parseInt(row.get("subMenuCount")));
        return menu;
    }


    @Given("^there is collection of elements (.+)$")
    public void thereIsCollectionOfElementsElementElement(List<String> list) {
        source = list;
    }

    @Then("source is not empty")
    public void sourceIsNotEmpty() {
        Assert.assertNotNull(source);
        Assert.assertFalse(source.isEmpty());
    }

    @Given("there is elements")
    public void thereIsElements(List<String> list) {
        source = list;
//        thereIsCollectionOfElementsElementElement(list);
    }

    @Given("There is a map of elements")
    public void thereIsAMapOfElements(Map<String, String> map) {
        sourceMap = map;
    }

    @Then("source map is not empty")
    public void sourceMapIsNotEmpty() {
        Assert.assertNotNull(sourceMap);
        Assert.assertFalse(sourceMap.isEmpty());
    }

    @Given("There is a map of integer elements")
    public void thereIsAMapOfIntegerElements(Map<String, Integer> map) {
        sourceIntegerMap = map;
    }

    @Then("source integer map is not empty")
    public void sourceIntegerMapIsNotEmpty() {
        Assert.assertNotNull(sourceIntegerMap);
        Assert.assertFalse(sourceIntegerMap.isEmpty());
    }

    @Given("There is a map of boolean elements")
    public void thereIsAMapOfBooleanElements(Map<String, Boolean> map) {
        sourceBooleanMap = map;
    }

    @Then("source boolean map is not empty")
    public void sourceBooleanMapIsNotEmpty() {
        Assert.assertNotNull(sourceBooleanMap);
        Assert.assertFalse(sourceBooleanMap.isEmpty());
    }

    @Given("menuObjectIsAvailable")
    public void menuObjectIsAvailable(List<Menu> menuList) {
        Assert.assertNotNull(menuList);
        Assert.assertFalse(menuList.isEmpty());
    }
}
