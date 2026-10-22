package it.academy.patterns.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Product> products = new ArrayList<>();

  public void addProduct(Product product){
    this.products.add(product);
  }

  public double getTotalPrice(){
    return products.stream().mapToDouble(Product::getPrice).sum();
  }

  public boolean containsProduct(String productName){
    return products.stream()
        .anyMatch(product -> product.getName().equalsIgnoreCase(productName));
  }
}
