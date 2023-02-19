package model;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationListTest {
    private DestinationList testCities;
    private City Vancouver;
    private City Richmond;
    private City Burnaby;
    private City London;
    private City France;

    @BeforeEach
    void runBefore() {
        Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        Richmond = new City("richmond", 3, Continent.NORTH_AMERICA);
        Burnaby = new City("burnaby", 1, Continent.NORTH_AMERICA);

        London = new City("london", 5, Continent.EUROPE);
        France = new City("london", 4, Continent.EUROPE);
    }

    @Test
    void testAddCity() {
        List<City> cities = new ArrayList<>();
        City Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        cities.add(Vancouver);

        assertTrue(cities.contains(Vancouver));
        assertEquals(1, cities.size());
    }

    @Test
    void testViewAllCities() {
        testCities.addCity(Vancouver);
        testCities.addCity(Richmond);
        testCities.addCity(Burnaby);
        testCities.addCity(London);
        testCities.addCity(France);

        List<City> testAllCities = testCities.getAllCities();

        assertEquals(5, testAllCities.size());
    }

    @Test
    void testViewCitiesByRating() {
        testCities.addCity(Vancouver);
        testCities.addCity(Richmond);
        testCities.addCity(Burnaby);
        testCities.addCity(London);
        testCities.addCity(France);

        List<City> testCitiesByRating = testCities.getCitiesByRating(5);

        assertEquals(2, testCitiesByRating.size());
        assertTrue(testCitiesByRating.contains(Vancouver));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
        assertFalse(testCitiesByRating.contains(France));
    }


    @Test
    void testViewCitiesByContinent() {
        testCities.addCity(Vancouver);
        testCities.addCity(Richmond);
        testCities.addCity(Burnaby);
        testCities.addCity(London);
        testCities.addCity(France);

        List<City> testCitiesByRating = testCities.getCitiesByContinent(Continent.EUROPE);

        assertEquals(2, testCitiesByRating.size());

        assertTrue(testCitiesByRating.contains(France));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Vancouver));
        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
    }


}
