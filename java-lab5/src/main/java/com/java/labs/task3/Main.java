package com.java.labs.task3;

import java.io.*;
import java.nio.file.Path;

public class Main {
    static void main() {

        Path inputPath = Path.of("./src/main/resources/input-test.txt");
        Path outputPath = Path.of("./src/main/resources/output-test.txt");
        Encoder encoder = new KeySymbolEncoder('d');
        Decoder decoder = new KeySymbolDecoder('d');

        try (EncodeInputStream eis = new EncodeInputStream(new FileInputStream(inputPath.toFile()), encoder);
             DecodeOutputStream dos = new DecodeOutputStream(new FileOutputStream(outputPath.toFile()), decoder)) {

            //Read bytes and encoded them
            byte[] encoded = eis.readAllBytes();

            //Print encoded string
            System.out.print("Encoded string: ");
            for (byte b : encoded) {
                System.out.print((char) b);
            }
            System.out.println();

            //Decode encoded bytes and write them to an output
            dos.write(encoded);

        } catch (IOException ex) {
            throw new RuntimeException("IO exception occurred during runtime!");
        } catch (NullPointerException ex) {
            throw new RuntimeException(ex);
        }
    }
}
