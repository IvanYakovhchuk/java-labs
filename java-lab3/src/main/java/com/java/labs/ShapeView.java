package com.java.labs;

import com.java.labs.localization.LocaleManager;

public class ShapeView {

    private final LocaleManager localeManager;

    public ShapeView(LocaleManager localeManager) {
        this.localeManager = localeManager;
    }

    private String resolve(String key) {
        return localeManager.get(key).replace("\\n", System.lineSeparator());
    }

    public void printMenu() {
        System.out.println(resolve("menu.prompt"));
    }

    public void printShapeChoice() {
        System.out.println(resolve("shape.choice"));
    }

    public void printMessage(String messageKey) {
        System.out.println(resolve(messageKey));
    }

    public <T> void printMessageAndValue(String messageKey, T value) {
        String message = resolve(messageKey);
        System.out.println(message + ": " + value.toString());
    }

}
