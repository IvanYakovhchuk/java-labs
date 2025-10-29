package com.java.labs.transport;

import com.java.labs.exceptions.NoAvailableSeatsException;
import com.java.labs.exceptions.NotAPassengerException;
import com.java.labs.people.Human;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract sealed class Vehicle<T extends Human> permits Car, Bus{

    private int totalSeats;
    private Set<T> passengers;

    public Vehicle(int totalSeats) {
        this.totalSeats = totalSeats;
        this.passengers = new LinkedHashSet<>();
    }

    public final int countOccupiedSeats() {
        return getPassengers().size();
    };

    public abstract boolean addPassenger(T t) throws NoAvailableSeatsException;

    public abstract boolean removePassenger(T t) throws NotAPassengerException;

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Set<T> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<T> passengers) {
        this.passengers = passengers;
    }
}
