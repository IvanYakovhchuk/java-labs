package com.java.labs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public final class ConverterTest {

    private static final Random rand = new Random();
    private static final Gson converter = new GsonBuilder().create();

    @Test
    public void testPersonEqualsAndHashcodeContract() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }

    @RepeatedTest(value = 5)
    public void testPersonBeforeAndAfterConvertingIsNotTheSameObject() {
        Person person = generateRandomPerson();
        String convertedPerson = converter.toJson(person);
        Person newPerson = converter.fromJson(convertedPerson, Person.class);
        Assertions.assertNotSame(person, newPerson);
    }

    @RepeatedTest(value = 5)
    public void testPersonBeforeAndAfterConvertingAreEqualObjects() {
        Person person = generateRandomPerson();
        String convertedPerson = converter.toJson(person);
        Person newPerson = converter.fromJson(convertedPerson, Person.class);
        Assertions.assertEquals(person, newPerson);
    }

    private Person generateRandomPerson() {
        return new Person(
                RandomStringUtils.randomAlphanumeric(1, 11),
                RandomStringUtils.randomAlphanumeric(1, 11),
                rand.nextInt(1, 100)
        );
    }
}
