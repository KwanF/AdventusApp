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
    private DestinationList testDestionationList;
    private City Vancouver;
    private City Richmond;
    private City Burnaby;
    private City London;
    private City France;

    @BeforeEach
    public void runBefore() {
        testDestionationList = new DestinationList();
        Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        Richmond = new City("richmond", 3, Continent.NORTH_AMERICA);
        Burnaby = new City("burnaby", 1, Continent.NORTH_AMERICA);

        London = new City("london", 5, Continent.EUROPE);
        France = new City("london", 4, Continent.EUROPE);
    }

    @Test
    public void testDestinationList() {
        testDestionationList = new DestinationList();
    }

    @Test
    public void testAddCity() {
        City Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        testDestionationList.addCity(Vancouver);

        assertTrue(testDestionationList.containsCity(Vancouver));
    }

    @Test
    public void testGetAllCities() {
        testDestionationList.addCity(Vancouver);
        testDestionationList.addCity(Richmond);
        testDestionationList.addCity(Burnaby);
        testDestionationList.addCity(London);
        testDestionationList.addCity(France);

        List<City> testAllCities = testDestionationList.getAllCities();

        assertEquals(5, testAllCities.size());
    }

    @Test
    public void testGetCitiesByRating() {
        testDestionationList.addCity(Vancouver);
        testDestionationList.addCity(Richmond);
        testDestionationList.addCity(Burnaby);
        testDestionationList.addCity(London);
        testDestionationList.addCity(France);

        List<City> testCitiesByRating = testDestionationList.getCitiesByRating(5);

        assertEquals(2, testCitiesByRating.size());
        assertTrue(testCitiesByRating.contains(Vancouver));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
        assertFalse(testCitiesByRating.contains(France));
    }


    @Test
    public void testGetCitiesByContinent() {
        testDestionationList.addCity(Vancouver);
        testDestionationList.addCity(Richmond);
        testDestionationList.addCity(Burnaby);
        testDestionationList.addCity(London);
        testDestionationList.addCity(France);

        List<City> testCitiesByRating = testDestionationList.getCitiesByContinent(Continent.EUROPE);

        assertEquals(2, testCitiesByRating.size());

        assertTrue(testCitiesByRating.contains(France));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Vancouver));
        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
    }


}
