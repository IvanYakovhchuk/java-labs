package com.java.labs.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ProducerConsumer {
    static void main() throws InterruptedException {

        ConcurrentCircularBuffer<String> b1 = new ConcurrentCircularBuffer<>(100);
        ConcurrentCircularBuffer<String> b2 = new ConcurrentCircularBuffer<>(100);

        startProcess(b1, b2);

        for (int i = 0; i < 100; i++) {
            System.out.println(b2.take());
        }
    }

    private static void startProcess(ConcurrentCircularBuffer<String> b1, ConcurrentCircularBuffer<String> b2) {
        ThreadFactory threadFactory = runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        };
        ExecutorService messageGenerator = Executors.newFixedThreadPool(5, threadFactory);
        ExecutorService messageSenders = Executors.newFixedThreadPool(2, threadFactory);

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            messageGenerator.submit(() -> {
                int msgNo = 1;
                while (true) {
                    String generatedMessage = String.format(
                            "Потік №%d згенерував повідомлення \"%d: Привіт від потоку №%d\"",
                            finalI, msgNo++, finalI
                    );
                    tryToPut(b1, generatedMessage);
                }
            });
        }

        for (int i = 6; i <= 7; i++) {
            int finalI = i;
            messageSenders.submit(() -> {
                while (true) {
                    String messageToSend = null;
                    try {
                        messageToSend = String.format("Потік №%d переклав повідомлення (%s)", finalI, b1.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    tryToPut(b2, messageToSend);
                }
            });
        }
    }

    private static void tryToPut(ConcurrentCircularBuffer<String> b2, String messageToSend) {
        try {
            b2.put(messageToSend);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
