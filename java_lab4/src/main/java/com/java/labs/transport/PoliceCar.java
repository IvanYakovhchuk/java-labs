package com.java.labs.transport;

import com.java.labs.people.Human;
import com.java.labs.people.Policeman;

public class PoliceCar extends Car<Policeman> {
    public PoliceCar(int totalSeats) {
        super(totalSeats);
    }

    @Override
    protected boolean isPassengerAllowed(Human h) {
        return h instanceof Policeman;
    }
}
