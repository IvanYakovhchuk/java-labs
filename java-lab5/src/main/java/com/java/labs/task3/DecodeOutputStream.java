package com.java.labs.task3;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DecodeOutputStream extends FilterOutputStream {

    private final Decoder decoder;

    public DecodeOutputStream(OutputStream out, Decoder decoder) {
        super(out);
        this.decoder = decoder;
    }

    @Override
    public void write(byte[] b) throws IOException {
        byte[] decodedString = decoder.decode(b);
        for (byte value : decodedString) {
            super.write(value);
        }
    }
}
