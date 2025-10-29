package com.java.labs;

import com.java.labs.exceptions.NoAvailableSeatsException;
import com.java.labs.exceptions.NotAPassengerException;
import com.java.labs.people.Firefighter;
import com.java.labs.people.Human;
import com.java.labs.people.Policeman;
import com.java.labs.transport.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehiclePassengerTests {

    @Test
    void busAndTaxiAcceptAnyPassengerTypes() {
        Bus<Human> bus = new Bus<>(2);
        Taxi<Human> taxi = new Taxi<>(2);

        Firefighter ff = new Firefighter();
        Policeman pm = new Policeman();

        bus.addPassenger(ff);
        bus.addPassenger(pm);
        assertEquals(2, bus.countOccupiedSeats());
        assertEquals(2, bus.getTotalSeats());

        taxi.addPassenger(pm);
        taxi.addPassenger(ff);
        assertEquals(2, taxi.countOccupiedSeats());
    }

    @Test
    void noAvailableSeatsThrowsWhenFull() {
        Taxi<Human> taxi = new Taxi<>(1);
        taxi.addPassenger(new Firefighter());
        assertThrows(NoAvailableSeatsException.class, () -> taxi.addPassenger(new Policeman()));
    }

    @Test
    void fireEngineOnlyAllowsFirefighters() {
        FireEngine fe = new FireEngine(1);
        fe.addPassenger(new Firefighter());
        assertEquals(1, fe.countOccupiedSeats());

        Car rawCar = new FireEngine(1);
        assertThrows(NotAPassengerException.class, () -> rawCar.addPassenger(new Policeman()));
    }

    @Test
    void policeCarOnlyAllowsPolicemen() {
        PoliceCar pc = new PoliceCar(1);
        pc.addPassenger(new Policeman());
        assertEquals(1, pc.countOccupiedSeats());

        Car rawCar = new PoliceCar(1);
        assertThrows(NotAPassengerException.class, () -> rawCar.addPassenger(new Firefighter()));
    }

    @Test
    void removingNonPresentPassengerThrows() {
        Bus<Human> bus = new Bus<>(2);
        Firefighter ff = new Firefighter();
        assertThrows(NotAPassengerException.class, () -> bus.removePassenger(ff));
    }

    @Test
    void occupiedSeatsUpdatesOnAddAndRemove() {
        Bus<Human> bus = new Bus<>(2);
        Firefighter ff = new Firefighter();

        assertEquals(0, bus.countOccupiedSeats());
        bus.addPassenger(ff);
        assertEquals(1, bus.countOccupiedSeats());
        bus.removePassenger(ff);
        assertEquals(0, bus.countOccupiedSeats());
    }

    @Test
    void countHumansOnRoad() {
        Road road = new Road();
        PoliceCar pc = new PoliceCar(2);
        FireEngine fe = new FireEngine(3);
        Bus<Human> bus = new Bus<>(15);

        for (int i = 0; i < pc.getTotalSeats(); i++) {
            pc.addPassenger(new Policeman());
        }

        for (int i = 0; i < fe.getTotalSeats(); i++) {
            fe.addPassenger(new Firefighter());
        }

        for (int i = 0; i < bus.getTotalSeats(); i++) {
            bus.addPassenger(new Human());
        }

        road.addCarToRoad(fe);
        road.addCarToRoad(pc);
        road.addCarToRoad(bus);

        int expectedHumanCount = pc.countOccupiedSeats() + fe.countOccupiedSeats() + bus.countOccupiedSeats();
        assertEquals(expectedHumanCount, road.getCountOfHumans());
    }
}