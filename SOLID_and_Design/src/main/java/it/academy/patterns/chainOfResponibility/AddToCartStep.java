package it.academy.patterns.chainOfResponibility;

public class AddToCartStep extends TestStep{
  @Override
  protected void performAction() {
    System.out.println("Adding to cart..");
  }
}
