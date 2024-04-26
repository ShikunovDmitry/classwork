package com.itacademy.aqa.reporting;


import io.qameta.allure.Allure;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Page
{
    private Logger logger = Logger.getLogger(Page.class);
 public Page(){
     logger.trace("Init elements of the page");
 }

 public void open(){
     logger.info("Opening page");
     Allure.attachment("Page","Opening page");
     logger.debug("Looking for element by locator");
     logger.debug("clicking by element");
 }
 public boolean isOpened(){
     logger.debug("Looking for element by locator");
     Allure.attachment("Page", "Checking that page is opened");
     logger.error("element was not found");
     return true;
 }

 public void clickByElement(String name){
     logger.debug("Looking for element by locator");
     logger.debug("clicking by element" + name);
 }

 public boolean isElementExist(String element){
     logger.debug("Looking for element by locator");
     logger.warn(" element is exist or not" + element);
     return true;
 }


}
