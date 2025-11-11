package com.java.labs;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.List;

import static java.lang.System.out;

public class ParallelMonteCarloPi {
    static void main() throws InterruptedException {
        long iterations = 100_000_000L;
        int threadsNumber = 100;
        double pi = calculatePi(iterations, threadsNumber);
        out.println("PI is " + pi);
        out.println("THREADS: " + threadsNumber);
        out.printf("ITERATIONS: %,d%n", iterations);
    }

    public static double calculatePi(long iterNum, int numberOfThreads) throws InterruptedException {
        long chunk = iterNum / numberOfThreads;
        List<Thread> threads = new ArrayList<>();
        long[] results = new long[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            final int index = i;
            Thread vt = Thread.ofVirtual().unstarted(() -> {
                long localCount = 0;
                for (int j = 0; j < chunk; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (x * x + y * y <= 1) {
                        localCount++;
                    }
                }
                results[index] = localCount;
//                out.println(Thread.currentThread().getName() + " finished working");
            });
//            vt.setName("Thread " + i);
            threads.add(vt);
        }

        LocalTime start = LocalTime.now();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        LocalTime end = LocalTime.now();
        out.printf(Locale.US, "TIME: %.2f ms%n", Duration.between(start, end).toNanos() / 1_000_000.0);

        long pointsInCircle = 0;
        for (long r : results) {
            pointsInCircle += r;
        }

        return 4.0 * pointsInCircle / iterNum;
    }

}
