package com.itacademy.aqa.cucumber;

public class Account {

    private int balance;

    private String lastResult;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance>=amount) {
            balance -= amount;
            lastResult = "Успех";
        } else {
            lastResult = "На счете недостаточно денег";
        }
    }

    public String getLastResult() {
        return lastResult;
    }
}
