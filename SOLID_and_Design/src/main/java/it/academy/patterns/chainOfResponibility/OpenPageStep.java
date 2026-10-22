package it.academy.patterns.chainOfResponibility;

public class OpenPageStep extends TestStep {
  @Override
  protected void performAction() {
    System.out.println("Web site opening");
  }
}
