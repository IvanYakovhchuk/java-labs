package com.java.labs;

public class ShapeView {

    public final String PROMPT = """
            1. Show the list of shapes
            2. Show total area
            3. Show total area for chosen type of shape
            4. Sort shapes by area
            5. Sort shapes by color""";

    public final String SHAPE_CHOICE = """
            1. Rectangle
            2. Triangle
            3. Circle""";

    public final String SUM = "Total area sum";

    public final String RECTANGLE_SUM = "Total area sum of rectangles";

    public final String TRIANGLE_SUM = "Total area sum of triangles";

    public final String CIRCLE_SUM = "Total area sum of circles";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public <T> void printMessageAndValue(String message, T value) {
        System.out.println(message + ": " + value.toString());
    }

}
