package com.itacademy.wordpress.pages;

import com.itacademy.wordpress.elements.MainMenu;
import com.itacademy.wordpress.elements.TopPageMenu;

public class BasePage {
  MainMenu mainMenu;
  TopPageMenu topPageMenu;


  protected MainMenu getMainMenu(){
    if(mainMenu == null){
      mainMenu = new MainMenu();
    }
    return mainMenu;
  }

  protected TopPageMenu getTopPageMenu(){
    if(topPageMenu == null){
      topPageMenu = new TopPageMenu();
    }
    return topPageMenu;
  }
}
