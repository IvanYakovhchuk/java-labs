package com.java.labs.shapes;

import java.util.Locale;

public class Triangle extends Shape {
    //first side
    private double a;
    //second side
    private double b;
    //third side
    private double c;

    public Triangle(String shapeColor, double a, double b, double c) {
        super(shapeColor);
        this.setA(a);
        this.setB(b);
        this.setC(c);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double calcArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Triangle{" +
                "a=" + "%.1f" +
                ", b=" + "%.1f" +
                ", c=" + "%.1f" +
                ", shapeColor='" + getShapeColor() + '\'' +
                '}', a, b, c);
    }
}
