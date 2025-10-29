package com.java.labs.transport;

import com.java.labs.exceptions.NoAvailableSeatsException;
import com.java.labs.exceptions.NotAPassengerException;
import com.java.labs.people.Human;

import java.util.Objects;

public non-sealed class Car<T extends Human> extends Vehicle<T> {

    public Car(int totalSeats) {
        super(totalSeats);
    }

    @Override
    public final boolean addPassenger(T t) throws NoAvailableSeatsException {
        Objects.requireNonNull(t);
        if (!isPassengerAllowed(t)) {
            throw new NotAPassengerException("Passenger of type " + t.getClass().getSimpleName()
                    + " is not allowed in this vehicle.");
        }
        if (this.getPassengers().size() == this.getTotalSeats()) {
            throw new NoAvailableSeatsException("There are no available seats in a car.");
        }
        return this.getPassengers().add(t);
    }

    protected boolean isPassengerAllowed(Human h) {
        return true;
    }

    @Override
    public final boolean removePassenger(T t) throws NotAPassengerException {
        if (this.getPassengers().stream().noneMatch(p -> p.equals(t))) {
            throw new NotAPassengerException("There are no such passengers in a car.");
        }
        return getPassengers().removeIf(p -> p.equals(t));
    }
}
