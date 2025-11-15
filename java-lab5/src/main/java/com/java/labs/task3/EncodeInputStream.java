package com.java.labs.task3;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncodeInputStream extends FilterInputStream {

    private static final int BUFFER_SIZE = 4096;
    private final Encoder encoder;
    private static final Logger logger = LoggingConfig.getLogger(EncodeInputStream.class);

    public EncodeInputStream(InputStream in, Encoder encoder) {
        super(in);
        this.encoder = encoder;
        logger.info("EncodeInputStream created with encoder: " + (encoder != null ? encoder.getClass().getName() : "null"));
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        logger.info("readAllBytes started");
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        try {
            while ((bytesRead = in.read(buffer)) != -1) {
                logger.fine("Read " + bytesRead + " bytes from underlying stream");
                byte[] chunk = Arrays.copyOf(buffer, bytesRead);
                byte[] encodedBytes = encoder.encode(chunk);
                logger.fine("Encoded chunk size: " + (encodedBytes != null ? encodedBytes.length : 0));
                result.write(encodedBytes);
            }
            logger.info("readAllBytes completed, total bytes: " + result.size());
            return result.toByteArray();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error during readAllBytes", e);
            throw e;
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Unexpected error during readAllBytes", e);
            throw e;
        }
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        logger.info("transferTo started");
        try {
            byte[] buffer = this.readAllBytes();
            out.write(buffer);
            logger.info("transferTo completed, transferred bytes: " + buffer.length);
            return buffer.length;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error during transferTo", e);
            throw e;
        }
    }
}
