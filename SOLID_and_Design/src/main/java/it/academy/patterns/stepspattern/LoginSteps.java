package it.academy.patterns.stepspattern;

public class LoginSteps {
  private LoginPage loginPage;

  public LoginSteps(LoginPage page){
    this.loginPage = page;
  }

  public void loginAndKeepSignedIn(String userName, String password){
    loginPage.enterUserName();
    loginPage.enterPassword();
    loginPage.checkKeepMeLoggedIn();
    loginPage.submit();
  }
}
