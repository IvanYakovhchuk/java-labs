package com.java.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static void main() throws IOException {
        Translator t = new Translator();

        t.addTranslation("London", "Лондон");
        t.addTranslation("is", "це");
        t.addTranslation("the", "");
        t.addTranslation("capital", "столиця");
        t.addTranslation("of", "");
        t.addTranslation("Great", "Великої");
        t.addTranslation("Britain", "Британії");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter your sentence: ");
            String input = br.readLine();
            IO.println("Translated sentence: " + t.translate(input));
        }


    }
}
