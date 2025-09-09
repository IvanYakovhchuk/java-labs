package com.architecture;

import java.util.*;

import static com.architecture.IntegerConverter.*;

public class Task1 {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter an integer: ");
            try {
                int num = sc.nextInt();
                System.out.println("Binary form: " + toBinary(num));
                System.out.println("Octal form: " + toOctal(num));
                System.out.println("Hexadecimal form: " + toHexadecimal(num));

            }
            catch (InputMismatchException e) {
                System.err.println("Your expression is either not an integer or out of range. Please try again!");
            }
        }

    }

}