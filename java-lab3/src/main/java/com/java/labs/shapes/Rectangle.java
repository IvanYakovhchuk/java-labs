package com.java.labs.shapes;

import java.util.Locale;

public class Rectangle extends Shape {
    //first side
    private double a;
    //second side
    private double b;

    public Rectangle(String shapeColor, double a, double b) {
        super(shapeColor);
        this.setA(a);
        this.setB(b);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double calcArea() {
        return a * b;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Rectangle{" +
                "a=" + "%.1f" +
                ", b=" + "%.1f" +
                ", shapeColor='" + "%s" + '\'' +
                '}', a, b, getShapeColor());
    }
}
