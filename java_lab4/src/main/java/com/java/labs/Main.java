package com.java.labs;

import com.java.labs.people.Policeman;
import com.java.labs.transport.PoliceCar;

public class Main {
    static void main() {
        Policeman h = new Policeman();

        PoliceCar pc = new PoliceCar(4);

        pc.addPassenger(h);
    }
}
