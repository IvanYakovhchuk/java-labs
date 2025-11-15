package com.java.labs;


import com.java.labs.localization.LocaleManager;
import com.java.labs.utils.CharInputUtility;

public class Main {

    public static void main(String[] args) {
        LocaleManager localeManager = new LocaleManager();
        ShapeController controller = new ShapeController(new ShapeModel(), new ShapeView(localeManager), new CharInputUtility(), localeManager);
        controller.execute();
    }
}
