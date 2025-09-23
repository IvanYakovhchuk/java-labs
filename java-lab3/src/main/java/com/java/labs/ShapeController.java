package com.java.labs;

import com.java.labs.shapes.Circle;
import com.java.labs.shapes.Rectangle;
import com.java.labs.shapes.Shape;
import com.java.labs.shapes.Triangle;
import com.java.labs.utils.CharInputUtility;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class ShapeController {

    private final ShapeModel model;
    private final ShapeView view;
    private final CharInputUtility ut;

    public ShapeController(ShapeModel model, ShapeView view, CharInputUtility ut) {
        this.model = model;
        this.view = view;
        this.ut = ut;
    }

    public void execute() {
        view.printMessage(view.PROMPT);
        int choice = ut.readDigit();
        Path path = Path.of("./src/main/resources/shapes.ser");
        switch (choice) {
            case 1:
                model.getShapes().forEach(System.out::println);
                break;
            case 2:
                view.printMessageAndValue(view.SUM, model.calcAreaSum());
                break;
            case 3:
                view.printMessage(view.SHAPE_CHOICE);
                int ch = ut.readDigit();
                switch (ch) {
                    case 1:
                        view.printMessageAndValue(view.RECTANGLE_SUM,
                                                  model.calcAreaSumOfShape(Rectangle.class));
                        break;
                    case 2:
                        view.printMessageAndValue(view.TRIANGLE_SUM,
                                                  model.calcAreaSumOfShape(Triangle.class));
                        break;
                    case 3:
                        view.printMessageAndValue(view.CIRCLE_SUM,
                                                  model.calcAreaSumOfShape(Circle.class));
                        break;
                    default: break;
                }
                break;
            case 4:
                model.sortShapesByAreaAsc()
                        .forEach(System.out::println);
                break;
            case 5:
                model.sortShapesByColorAsc()
                        .forEach(System.out::println);
                break;
            case 6:
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                    model.serializeShapes(oos);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 7:
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                    List<Shape> result = model.deserializeShapes(ois);
                    result.forEach(System.out::println);
                } catch (EOFException ex) {
                    System.err.println("File is empty.");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            default: break;
        }
    }
}
