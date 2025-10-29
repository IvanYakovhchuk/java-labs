package com.java.labs;

import com.java.labs.people.Human;
import com.java.labs.transport.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class Road {

    public Set<Vehicle<? extends Human>> carsInRoad = new HashSet<>();

    public int getCountOfHumans() {
        return carsInRoad.stream()
                .mapToInt(Vehicle::countOccupiedSeats)
                .sum();
    }

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
    }

}
