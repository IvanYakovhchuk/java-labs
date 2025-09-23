package com.java.labs.utils;

import java.util.Scanner;

public class CharInputUtility {
    private static final Scanner scanner = new Scanner(System.in);

    public int readDigit() {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1 && Character.isDigit(input.charAt(0))) {
                return Character.getNumericValue(input.charAt(0));
            }
            System.out.println("Invalid input. Please choose a valid option.");
        }
    }
}
