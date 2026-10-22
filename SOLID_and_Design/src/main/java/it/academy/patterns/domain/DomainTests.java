package it.academy.patterns.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DomainTests {
  @Test
  public void addCartTest() {

    Product product1 = new Product("IPhone8", 700);
    Product product2 = new Product("RTX 4070", 900);
    Cart cart = new Cart();
    cart.addProduct(product1);
    cart.addProduct(product2);

    Assertions.assertTrue(cart.containsProduct("RTX 4070"));

    Assertions.assertEquals(1600, cart.getTotalPrice());
  }
}
