package main.java.com.realstateapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
RealEstateApp is a project stub for real estate agents. There are three classes available:

1. Apartment (with a given area and price).
2. ApartmentRater, which rates the price/area ratio for a given apartment. There are three possible ratings: 0 (best price/area ratio), 1 or 2 (worst price/area ratio).
3. RandomApartmentGenerator, which generates an apartment with a random price and area. If you invoke the constructor with no parameters, the default values are used: minimum area of 30.0 square meters and minimum price per square meter of 3000.0. You can also specify your own minimum area and price. In either case, the maximum values are: mimum values * 4.0.

Here are my suggestions as to what tests you can write:

1. For ApartmentRater:
a. should_ReturnCorrectRating_When_CorrectApartment -- write a parameterized test with different values
b. should_ReturnErrorValue_When_IncorrectApartment
c. should_CalculateAverageRating_When_CorrectApartmentList
d. should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList

2. For RandomApartmentGenerator (since values are randomly generated, repeat the tests multiple times):
a. should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice
b. should_GenerateCorrectApartment_When_CustomMinAreaMinPrice

If you can come up with any other tests, that's even better! The solution to the exercises, however, will only contain the tests mentioned above.
*/
class ApartmentRaterTest {

    @ParameterizedTest
    @CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
    void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) {
        // given
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        int expected = rating;
        // when
        int actual = ApartmentRater.rateApartment(apartment);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        // given
        Apartment apartment = new Apartment(0.0, new BigDecimal(350000.0));
        int expected = -1;
        // when
        int actual = ApartmentRater.rateApartment(apartment);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
        // given
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(72.0, new BigDecimal(250000.0)));
        apartments.add(new Apartment(48.0, new BigDecimal(350000.0)));
        apartments.add(new Apartment(30.0, new BigDecimal(600000.0)));
        double expected = 1.0;
        // when
        double actual = ApartmentRater.calculateAverageRating(apartments);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
        // given
        List<Apartment> apartments = new ArrayList<>();
        // when
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
        // then
        assertThrows(RuntimeException.class, executable);
    }
}