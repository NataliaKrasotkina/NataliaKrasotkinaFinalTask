package service;

import dto.Address;
import net.datafaker.Faker;

import java.util.Locale;

public class DataService {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static Address getAddress() {
        return Address.builder()
                .streetAddress(faker.address().streetName())
                .country(faker.address().country())
                .city(faker.address().city())
                .phone(faker.phoneNumber().phoneNumber())
                .zipCode(faker.address().zipCode()).build();
    }
}