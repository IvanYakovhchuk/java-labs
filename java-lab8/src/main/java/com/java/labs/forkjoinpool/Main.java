package com.java.labs.forkjoinpool;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int SIZE = 1_000_000;
    private static final Random random = new Random();

    static void main() {

        int[] arr = new int[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0, 2);
        }

        RecursiveArraySplittingTask task1 = new RecursiveArraySplittingTask(arr);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        LocalTime start1 = LocalTime.now();
        long result1 = pool.invoke(task1);
        LocalTime end1 = LocalTime.now();
        System.out.println(result1);
        System.out.printf(Locale.US, "TIME: %.2f ms%n", Duration.between(start1, end1).toNanos() / 1_000_000.0);
    }
}
