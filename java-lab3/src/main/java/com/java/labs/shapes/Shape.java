package com.java.labs.shapes;

import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {

    private String shapeColor;

    public Shape(String shapeColor) {
        this.setShapeColor(shapeColor);
    }

    public abstract double calcArea();

    public String getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    @Override
    public void draw() {

    }

    @Override
    public String toString() {
        return "Shape{" +
                "shapeColor='" + shapeColor + '\'' +
                '}';
    }
}
