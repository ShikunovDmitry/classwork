package com.itacademy.aqa.cucumber.stepdefs;

import com.itacademy.aqa.cucumber.Account;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AccountStepdefs {
    Account account = new Account();

    @Given("^на счете пользователя имеется (\\d+) рублей$")
    public void thereAreAmountOfMoneyOnUsersAccount(int balance) {
        account.setBalance(balance);
    }

    @When("пользователь снимает {int} рублей")
    public void пользовательСнимаетРублей(int amount) {
        account.withdraw(amount);
    }

    @Then("на счете остается {int} рублей")
    public void наСчетеОстаетсяРублей(int expectedBalance) {
        Assert.assertEquals("Балане не соответствует ожидаемому", expectedBalance,account.getBalance());
    }

    @Then("появляется предупреждение {string}")
    public void появляетсяПредупреждение(String message) {
        Assert.assertEquals(message, account.getLastResult());
    }

    @And("(.*) довол(.+)")
    public void яДоволен() {
        Assert.assertTrue(account.getBalance()>0);
    }
}
