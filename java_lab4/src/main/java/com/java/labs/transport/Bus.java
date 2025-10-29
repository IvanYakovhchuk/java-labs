package com.java.labs.transport;

import com.java.labs.exceptions.NoAvailableSeatsException;
import com.java.labs.exceptions.NotAPassengerException;
import com.java.labs.people.Human;

public non-sealed class Bus<T extends Human> extends Vehicle<T> {

    public Bus(int totalSeats) {
        super(totalSeats);
    }

    @Override
    public final boolean addPassenger(T t) throws NoAvailableSeatsException {
        if (this.getPassengers().size() == this.getTotalSeats()) {
            throw new NoAvailableSeatsException("There are no available seats in a bus.");
        }
        return this.getPassengers().add(t);
    }

    @Override
    public final boolean removePassenger(T t) throws NotAPassengerException {
        if (this.getPassengers().stream().noneMatch(p -> p.equals(t))) {
            throw new NotAPassengerException("There are no such passengers in a bus.");
        }
        return this.getPassengers().removeIf(p -> p.equals(t));
    }
}
