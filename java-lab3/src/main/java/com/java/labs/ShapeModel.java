package com.java.labs;

import com.java.labs.shapes.Circle;
import com.java.labs.shapes.Rectangle;
import com.java.labs.shapes.Shape;
import com.java.labs.shapes.Triangle;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ShapeModel {

    private List<Shape> shapes;

    public ShapeModel() {
        this.setShapes(initializeShapes());
    }

    public ShapeModel(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    private List<Shape> initializeShapes() {
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

    public List<Shape> sortShapesByColorAsc() {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getShapeColor);
        return shapes.stream()
                .sorted(comparator)
                .toList();
    }

    public List<Shape> sortShapesByAreaAsc() {
        Comparator<Shape> comparator = Comparator.comparingDouble(Shape::calcArea);
        return shapes.stream()
                .sorted(comparator)
                .toList();
    }

    public double calcAreaSum() {
        return shapes.stream()
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    public double calcAreaSumOfShape(Class<? extends Shape> c) {
        return shapes.stream()
                .filter(c::isInstance)
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    public void serializeShapes(ObjectOutputStream oos) {
        shapes.forEach(s -> {
            try {
                oos.writeObject(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Shape> deserializeShapes(ObjectInputStream ois) {
        List<Shape> deserializedShapes = new ArrayList<>();
        boolean isEmpty = true;
        while (true) {
            try {
                deserializedShapes.add((Shape) ois.readObject());
                isEmpty = false;
            } catch (EOFException ex) {
                if (isEmpty) {
                    System.err.println("The file is empty.");
                }
                break;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return deserializedShapes;
    }
}
