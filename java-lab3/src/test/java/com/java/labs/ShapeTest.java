package com.java.labs;

import com.java.labs.shapes.Circle;
import com.java.labs.shapes.Rectangle;
import com.java.labs.shapes.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ShapeTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/rectangles_areas.csv")
    public void shouldCalculateRectangleArea(String color,
                                             double a, double b,
                                             double expectedArea) {
        Rectangle rectangle = new Rectangle(color, a, b);
        Assertions.assertEquals(expectedArea, rectangle.calcArea());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/triangles_areas.csv")
    public void shouldCalculateTriangleArea(String color,
                                            double a, double b, double c,
                                            double expectedArea) {
        Triangle triangle = new Triangle(color, a, b, c);
        Assertions.assertEquals(expectedArea, Math.round(triangle.calcArea() * 100.0) / 100.0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/circles_areas.csv")
    public void shouldCalculateCircleArea(String color, double radius,
                                          double expectedArea) {
        Circle circle = new Circle(color, radius);
        Assertions.assertEquals(expectedArea, Math.round(circle.calcArea() * 100.0) / 100.0);
    }
}
