package com.java.labs.task1;

public class Account {
    private int balance;

    public Account(int sum) {
        this.balance = sum;
    }

    public Account() {
        this.balance = 0;
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
