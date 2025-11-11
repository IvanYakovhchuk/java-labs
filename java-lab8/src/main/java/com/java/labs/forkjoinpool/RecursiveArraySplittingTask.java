package com.java.labs.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class RecursiveArraySplittingTask extends RecursiveTask<Long> {
    private final int[] arr;

    public RecursiveArraySplittingTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    public Long compute() {
        int CHUNK_SIZE = 20;
        if (arr.length > CHUNK_SIZE) {
            RecursiveArraySplittingTask firstHalfArraySplittingTask = new RecursiveArraySplittingTask(Arrays.copyOfRange(arr, 0, arr.length/2));
            RecursiveArraySplittingTask secondHalfArraySplittingTask = new RecursiveArraySplittingTask(Arrays.copyOfRange(arr, arr.length/2, arr.length));
            firstHalfArraySplittingTask.fork();
            secondHalfArraySplittingTask.fork();
            return firstHalfArraySplittingTask.join() + secondHalfArraySplittingTask.join();
        }
        else {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Arrays.stream(arr)
                    .asLongStream()
                    .sum();
        }
    }
}
