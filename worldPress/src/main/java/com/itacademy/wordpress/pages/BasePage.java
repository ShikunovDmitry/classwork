package com.itacademy.wordpress.pages;

import com.itacademy.wordpress.elements.MainMenu;
import com.itacademy.wordpress.elements.TopPageMenu;

public class BasePage {
  MainMenu mainMenu;
  TopPageMenu topPageMenu;


  public MainMenu getMainMenu(){
    if(mainMenu == null){
      mainMenu = new MainMenu();
    }
    return mainMenu;
  }

  public TopPageMenu getTopPageMenu(){
    if(topPageMenu == null){
      topPageMenu = new TopPageMenu();
    }
    return topPageMenu;
  }
}
