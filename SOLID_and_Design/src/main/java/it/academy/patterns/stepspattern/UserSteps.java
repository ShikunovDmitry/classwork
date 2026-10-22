package it.academy.patterns.stepspattern;

import org.junit.jupiter.api.Assertions;

public class UserSteps {
  private boolean isRegistred;
  private boolean isLoggedIn;

  public UserSteps openHomePage(){
    System.out.println("Opening a home page");
    return this;
  }

  public UserSteps registerUser( ){
    System.out.println("Register user");
    isRegistred =true;
    return this;
  }

  public UserSteps loginUser(){
    System.out.println("Login in user");
    LoginPage loginPage = new LoginPage();
    loginPage.enterUserName();
    loginPage.enterPassword();
    loginPage.submit();
    if(isRegistred) {
      isLoggedIn = true;
    }
    return this;
  }

  public UserSteps checkingUser(){
    System.out.println("Asserting user");
      Assertions.assertTrue(isLoggedIn);
    return this;
  }

}
