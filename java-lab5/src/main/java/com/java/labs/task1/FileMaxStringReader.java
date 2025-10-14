package com.java.labs.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class FileMaxStringReader {

    private static String[] getAllStrings(Path filePath) throws IOException {
        return Files.readAllLines(filePath).toArray(new String[0]);
    }

    private static String findMaxString(String[] strings) {
        return Arrays.stream(strings)
                .reduce((s1, s2) -> {
                    if (s1.length() >= s2.length()) {
                        return s1;
                    } else return s2;
                })
                .orElseThrow(() -> new RuntimeException("String with maximal length was not found."));
    }

    public static String getMaxString(Path filePath) throws IOException {
        return findMaxString(getAllStrings(filePath));
    }

}
