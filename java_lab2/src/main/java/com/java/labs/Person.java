package com.java.labs;

import java.util.Objects;

public class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.age = age;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person other)) {
            return false;
        }
        return Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.firstName, other.firstName)
                && this.age == other.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lastName, this.firstName, this.age);
    }
}
