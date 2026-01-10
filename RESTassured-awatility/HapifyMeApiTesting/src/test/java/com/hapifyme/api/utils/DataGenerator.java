package com.hapifyme.api.utils;

import com.github.javafaker.Faker;

public class DataGenerator {
    private static final Faker FAKER = new Faker();

    public static String generateUniqueEmail() {
        return "user_" + System.currentTimeMillis() + "@test.com";
    }

    public static String generateFirstName() { return FAKER.name().firstName(); }
    public static String generateLastName() { return FAKER.name().lastName(); }
}