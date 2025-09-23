package com.java.labs;


import com.java.labs.utils.CharInputUtility;

public class Main {

    public static void main(String[] args) {
        ShapeController controller = new ShapeController(new ShapeModel(), new ShapeView(), new CharInputUtility());
        controller.execute();
    }
}
