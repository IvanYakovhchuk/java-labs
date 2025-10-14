package com.java.labs.task4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class Main {

    static void main() throws URISyntaxException, IOException {

        String html;
        try {
            URL url = getUrl();
            html = getHtmlFromUrl(url);
        } catch (RuntimeException e) {
            System.err.println("You entered an invalid URL!");
            return;
        }

        Document doc = Jsoup.parse(html);

        Map<String, Integer> freqMap = new HashMap<>();

        doc.getAllElements()
                .forEach(el -> {
                    freqMap.put(el.tagName(), freqMap.getOrDefault(el.tagName(), 0) + 1);
                });

        System.out.printf("| %-20s | %-15s |%n", "Tag", "Frequency");
        System.out.println("------------------------------------------------");

        freqMap.forEach((tag, count) -> System.out.printf("| %-20s | %-15d |%n", tag, count));

    }

    private static String getHtmlFromUrl(URL url) {
        String html;
        try (HtmlReader reader = new HtmlReader(url)) {
            html = reader.readAllAsString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return html;
    }

    private static URL getUrl() throws IOException, URISyntaxException {
        String urlString;
        URL url;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter the url you want to count tags in: ");
            urlString = br.readLine();
        }

        try {
            url = new URI(urlString).toURL();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

}
