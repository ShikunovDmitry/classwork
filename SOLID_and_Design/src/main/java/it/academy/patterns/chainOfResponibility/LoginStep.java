package it.academy.patterns.chainOfResponibility;

public class LoginStep extends TestStep{
  @Override
  protected void performAction() {
    System.out.println("Perform Login..");
  }
}
