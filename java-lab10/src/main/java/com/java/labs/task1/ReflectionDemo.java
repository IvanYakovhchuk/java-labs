package com.java.labs.task1;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class ReflectionDemo {
    static void main() throws IllegalAccessException, NoSuchFieldException {
        String string = "string";
//        String myString = IO.readln("Enter the string: ");

        System.out.println("Before: " + string);

        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        byte[] value = (byte[]) valueField.get(string);

        String newString = "new string";
//        String myNewString = IO.readln("Enter new string: ");

        byte[] bytes = newString.getBytes(StandardCharsets.UTF_8);
        valueField.set(string, bytes);

        System.out.println("After: " + string);
    }
}
