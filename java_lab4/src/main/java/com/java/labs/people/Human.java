package com.java.labs.people;

import java.util.Objects;

public class Human {

    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Human(Human human) {
        this.name = human.getName();
        this.age = human.getAge();
    }

    public Human() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Human human)) return false;
        return age == human.age && Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
