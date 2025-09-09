package com.java.labs;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Shape> myShapes = initializeShapes();
        System.out.print("1. ");
        myShapes.forEach(System.out::println);
        System.out.printf(Locale.US,"\n2. Total area sum: %.1f%n", calcAreaSum(myShapes));
        System.out.printf(Locale.US, "\n3. Total area of rectangles: %.1f%n" +
                "Total area of triangles: %.1f%n" + "Total area of circles: %.1f%n",
                calcAreaSumOfShape(Rectangle.class, myShapes),
                calcAreaSumOfShape(Triangle.class, myShapes),
                calcAreaSumOfShape(Circle.class, myShapes));
        System.out.print("\n4. ");
        sortShapesByAreaAsc(myShapes).forEach(shape -> System.out.println(shape + "     Area: " + shape.calcArea()));
        System.out.print("\n5. ");
        sortShapesByColorAsc(myShapes).forEach(shape -> System.out.println(shape + "      Color: " + shape.getShapeColor()));
    }

    private static List<Shape> sortShapesByColorAsc(List<Shape> unsortedShapes) {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getShapeColor);
        return unsortedShapes.stream()
                .sorted(comparator)
                .toList();
    }

    private static List<Shape> sortShapesByAreaAsc(List<Shape> unsortedShapes) {
        Comparator<Shape> comparator = Comparator.comparingDouble(Shape::calcArea);
        return unsortedShapes.stream()
                .sorted(comparator)
                .toList();
    }

    private static double calcAreaSum(List<Shape> shapes) {
        return shapes.stream()
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    private static double calcAreaSumOfShape(Class<? extends Shape> c, List<Shape> shapes) {
        return shapes.stream()
                .filter(c::isInstance)
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    private static List<Shape> initializeShapes() {
        Shape[] result = new Shape[15];
        String[] shapeTypes = {"circle", "rectangle", "triangle"};
        String[] shapeColors = {"red", "green", "yellow", "white", "black", "blue", "purple"};
        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            switch (shapeTypes[rand.nextInt(0, 3)]) {
                case "circle":
                    Circle circle = new Circle(
                        shapeColors[rand.nextInt(0, 7)],
                        rand.nextDouble() * 10 + 1
                    );
                    result[i] = circle;
                    break;
                case "rectangle":
                    Rectangle rectangle = new Rectangle(
                        shapeColors[rand.nextInt(0, 7)],
                        rand.nextDouble() * 50 + 1,
                        rand.nextDouble() * 50 + 1
                    );
                    result[i] = rectangle;
                    break;
                case "triangle":
                    double a, b, c;
                    do {
                        a = rand.nextDouble() * 50 + 1;
                        b = rand.nextDouble() * 50 + 1;
                        c = rand.nextDouble() * 50 + 1;
                    } while (!(a + b > c && a + c > b && b + c > a));

                    Triangle triangle = new Triangle(
                        shapeColors[rand.nextInt(0, 7)],
                        a, b, c
                    );
                    result[i] = triangle;
                    break;
                default: break;
            }
        }
        return Arrays.asList(result);
    }
}
