package com.java.labs;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Translator {

    HashMap<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public Translator(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public void addTranslation(String original, String translated) {
        dictionary.put(original, translated);
    }

    public String translate(String original) {
        String[] words = original.split(" ");
        List<String> translatedWords = new ArrayList<>();
        String temp;
        for (String word : words) {
            temp = dictionary.computeIfPresent(word, (k, v) -> v);
            translatedWords.add(temp);
        }
        return String.join(" ", translatedWords);
    }
}
