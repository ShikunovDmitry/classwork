package tests.potests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.po.CartPage;
import pages.po.CartPageElement;
import pages.po.InventoryPage;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class CartTests extends BasePOTest {

  @Test
  public void addToCartTest() {

    String itemToBuy = "Sauce Labs Bike Light";
    InventoryPage inventoryPage = new InventoryPage();
    inventoryPage.addItemToCart(itemToBuy);
    inventoryPage.goToCart();

    CartPage cartPage = new CartPage();

    Assert.assertTrue(cartPage.isOpened());

    List<CartPageElement> cartItems = cartPage.getItems();

    Assert.assertEquals(cartItems.size(), 1);

    AtomicBoolean itemFound = new AtomicBoolean(false);

    cartItems.stream().forEach(cartItem -> {
      if (cartItem.getElementLinkText().equals(itemToBuy)) {
        itemFound.set(true);
      }
    });
    Assert.assertTrue(itemFound.get(), "Item Not Found " + itemToBuy);


  }

  @Test
  public void removeFromCartTest() {

    String itemToBuy = "Sauce Labs Bike Light";
    String secondItemToBuy = "Sauce Labs Onesie";

    InventoryPage inventoryPage = new InventoryPage();
    inventoryPage.addItemToCart(itemToBuy);
    inventoryPage.addItemToCart(secondItemToBuy);
    inventoryPage.goToCart();

    CartPage cartPage = new CartPage();

    Assert.assertTrue(cartPage.isOpened());

    Assert.assertEquals(cartPage.getItems().size(), 2);


    cartPage.removeItemFromCart(itemToBuy);

    //First element removed
    AtomicBoolean itemFound = new AtomicBoolean(false);
    cartPage.getItems().stream().forEach(cartItem -> {
      if (cartItem.getElementLinkText().equals(itemToBuy)) {
        itemFound.set(true);
      }
    });
    Assert.assertFalse(itemFound.get(), "Item Found " + itemToBuy);

    //Second element is not removed
    AtomicBoolean secondItemFound = new AtomicBoolean(false);
    cartPage.getItems().stream().forEach(cartItem -> {
      if (cartItem.getElementLinkText().equals(secondItemToBuy)) {
        secondItemFound.set(true);
      }
    });
    Assert.assertTrue(secondItemFound.get(), "Item Not Found " + secondItemToBuy);


  }
}
