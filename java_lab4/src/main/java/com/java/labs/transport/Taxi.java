package com.java.labs.transport;

import com.java.labs.people.Human;

public class Taxi<T extends Human> extends Car<T> {
    public Taxi(int totalSeats) {
        super(totalSeats);
    }
}
