package com.java.labs.shapes;

import java.util.Locale;

public class Circle extends Shape {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Circle{" +
                "radius=" + "%.1f" +
                ", shapeColor='" + "%s" + '\'' +
                '}', radius, getShapeColor());

    }
}
