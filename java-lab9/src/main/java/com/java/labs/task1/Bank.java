package com.java.labs.task1;

public class Bank {

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public void transfer(Account from, Account to, int amount) throws InterruptedException{
        Account firstToLock = from.hashCode() < to.hashCode() ? from : to;
        Account secondToLock = from.hashCode() < to.hashCode() ? to : from;
        synchronized (firstToLock) {
            synchronized (secondToLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
                else {
                    System.err.println("Недостатньо коштів для виконання транзакції! Перевірте рахунок.");
                }
            }
        }
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public void transferWithPossibleDeadlock(Account from, Account to, int amount) throws InterruptedException {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " заблокував " + from.hashCode());
            Thread.sleep(50);
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " заблокував " + to.hashCode());
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                } else {
                    System.err.println("Недостатньо коштів для виконання транзакції! Перевірте рахунок.");
                }
            }
        }
    }

    public void transferUnsynchronized(Account from, Account to, int amount) throws InterruptedException {
        if (from.withdraw(amount)) {
            to.deposit(amount);
        } else {
            System.err.println("Недостатньо коштів для виконання транзакції! Перевірте рахунок.");
        }
    }
}
