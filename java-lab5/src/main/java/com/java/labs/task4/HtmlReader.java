package com.java.labs.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlReader extends BufferedReader {

    public HtmlReader(URL url) throws IOException {
        super(new BufferedReader(new InputStreamReader(url.openStream())));
    }

}
