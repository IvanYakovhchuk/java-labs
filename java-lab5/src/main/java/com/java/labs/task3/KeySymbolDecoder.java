package com.java.labs.task3;

import java.util.Arrays;

public record KeySymbolDecoder(char keySymbol) implements Decoder {

    @Override
    public byte[] decode(byte[] encodedString) {
        byte[] input = Arrays.copyOfRange(encodedString, 0, encodedString.length);
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = (byte) (input[i] - (byte) keySymbol);
        }
        return result;
    }
}
