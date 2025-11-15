package com.java.labs.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {

    private Locale locale;
    private ResourceBundle bundle;
    private static final String BASE_NAME = "location.messages";

    public LocaleManager() {
        this(Locale.ENGLISH);
    }

    public LocaleManager(Locale locale) {
        this.locale = locale;
        loadBundle();
    }

    private void loadBundle() {
        bundle = ResourceBundle.getBundle(BASE_NAME, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        loadBundle();
    }

    public void setLocaleByChoice(int choice) {
        switch (choice) {
            case 2 -> setLocale(new Locale("uk"));
            case 1 -> setLocale(Locale.ENGLISH);
            default -> setLocale(Locale.ENGLISH);
        }
    }

    public String get(String key) {
        return bundle.getString(key);
    }
}
