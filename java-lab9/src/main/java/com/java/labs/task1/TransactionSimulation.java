package com.java.labs.task1;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransactionSimulation {

    public static Random rand = new Random();

    static void main() throws InterruptedException {
        int accountCount = 200;
        int threadCount = 2000;
        Bank bank = new Bank();

        List<Account> accounts = new ArrayList<>(accountCount);
        for (int i = 0; i < accountCount; i++) {
            accounts.add(new Account(rand.nextInt(200, 700000)));
        }
        long sumBeforeTest = accounts.stream().mapToLong(Account::getBalance).sum();
        System.out.println("Сума грошей на всіх рахунках до тестування транзакцій: " + sumBeforeTest);

        testTransaction(threadCount, accountCount, accounts, bank);

        long sumAfterTest = accounts.stream().mapToLong(Account::getBalance).sum();
        System.out.println("Сума грошей на всіх рахунках після тестування транзакцій: " + sumAfterTest);

        if (sumAfterTest == sumBeforeTest) {
            System.out.println("Транзакції пройшли успішно, гроші збереглися!");
        } else {
            System.err.println("Транзакції провалилися, гроші втрачено!");
        }

    }

    private static void testTransaction(int threadCount, int accountCount, List<Account> accounts, Bank bank) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    int fromIndex = rand.nextInt(accountCount);
                    int toIndex;
                    do {
                        toIndex = rand.nextInt(accountCount);
                    } while (toIndex == fromIndex);

                    Account from = accounts.get(fromIndex);
                    Account to = accounts.get(toIndex);

                    int amount = rand.nextInt(1000);

                    try {
                        bank.transfer(from, to, amount);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            executor.shutdown();
            boolean isTerminated = executor.awaitTermination(1, TimeUnit.MINUTES);
            if (isTerminated) {
                System.out.println("Всі потоки завершили роботу.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static void deadlock() throws InterruptedException {
        Account a1 = new Account(100);
        Account a2 = new Account(100);
        Bank bank = new Bank();

        Thread t1 = new Thread(() -> {
            try {
                bank.transfer(a1, a2, 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                bank.transfer(a2, a1, 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Готово (якщо не дедлок)");
    }
}
