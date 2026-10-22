package it.academy.patterns.chainOfResponibility;

import org.junit.jupiter.api.Test;

public class TestStepTest {

  @Test
  public void checkAddingToCart(){
    TestStep openPage = new OpenPageStep();
    TestStep loginStep = new LoginStep();
    TestStep addToCartStep = new AddToCartStep();
    TestStep checkoutStep = new CheckoutStep();

    openPage
        .setNextStep(loginStep)
        .setNextStep(addToCartStep)
        .setNextStep(checkoutStep);

    openPage.execute();
  }
}
