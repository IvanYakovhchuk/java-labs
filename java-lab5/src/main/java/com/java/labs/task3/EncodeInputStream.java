package com.java.labs.task3;

import java.io.*;
import java.util.Arrays;

public class EncodeInputStream extends FilterInputStream {

    private static final int BUFFER_SIZE = 4096;
    private final Encoder encoder;

    public EncodeInputStream(InputStream in, Encoder encoder) {
        super(in);
        this.encoder = encoder;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = in.read(buffer)) != -1) {
            byte[] encodedBytes = encoder.encode(Arrays.copyOf(buffer, bytesRead));
            result.write(encodedBytes);
        }

        return result.toByteArray();
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        byte[] buffer = this.readAllBytes();
        out.write(buffer);
        return buffer.length;
    }
}
