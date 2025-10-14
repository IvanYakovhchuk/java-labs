package com.java.labs.task1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    static void main() {
        try {
            Path path = Paths.get(Objects.requireNonNull(Main.class.getClassLoader().getResource("input-test.txt")).toURI());
            System.out.println(FileMaxStringReader.getMaxString(path));
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (NullPointerException ex) {
            throw new RuntimeException("There is no such file in system.");
        } catch (IOException ex) {
            throw new RuntimeException("IO exception occurred during runtime.");
        }
    }
}
