package com.java.labs.transport;

import com.java.labs.people.Firefighter;
import com.java.labs.people.Human;

public class FireEngine extends Car<Firefighter> {
    public FireEngine(int totalSeats) {
        super(totalSeats);
    }

    @Override
    protected boolean isPassengerAllowed(Human h) {
        return h instanceof Firefighter;
    }
}
