package it.academy.patterns.chainOfResponibility;

public class CheckoutStep extends TestStep{
  @Override
  protected void performAction() {
    System.out.println("Perform checkout");
  }
}
