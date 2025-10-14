package com.java.labs.task3;

import java.util.Arrays;

public record KeySymbolEncoder(char keySymbol) implements Encoder {

    @Override
    public byte[] encode(byte[] input) {
        byte[] bytes = Arrays.copyOfRange(input, 0, input.length);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] + (byte) keySymbol);
        }
        return bytes;
    }

}
