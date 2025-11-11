package com.java.labs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Main {
    static void main() {
        List<Long> perfectNumbers = mersennRealization();

        perfectNumbers.forEach(n -> {
            String divisors = LongStream.rangeClosed(1, n / 2)
                    .filter(i -> n % i == 0)
                    .mapToObj(String::valueOf)
                    .reduce((a, b) -> a + " + " + b)
                    .orElse("");
            System.out.println(n + " = " + divisors);
        });
    }

    public static List<Long> simpleRealization() {
        String input = IO.readln("Enter the number: ");
        int num = 0;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Your input does not contain a number!");
            System.exit(1);
        }
        return LongStream.rangeClosed(2, num)
                .filter(n -> {
                    long sum = 1;
                    int sqrt = (int) Math.sqrt(n);
                    for (int i = 2; i <= sqrt; i++) {
                        if (n % i == 0) {
                            sum += i;
                            long other = n / i;
                            if (other != i) sum += other;
                        }
                    }
                    return sum == n;
                })
                .boxed()
                .toList();
    }

    public static List<Long> mersennRealization() {
        int[] mersennNumbers = {2, 3, 5, 7, 13, 17};
        return Arrays.stream(mersennNumbers)
                .mapToLong(p -> (1L << (p - 1)) * ((1L << p) - 1))
                .boxed()
                .toList();
    }
}
