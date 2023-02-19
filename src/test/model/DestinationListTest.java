package model;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationListTest {
    private DestinationList testDestinationList;
    private City Vancouver;
    private City Richmond;
    private City Burnaby;
    private City London;
    private City France;

    @BeforeEach
    public void runBefore() {
        testDestinationList = new DestinationList();
        Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        Richmond = new City("richmond", 3, Continent.NORTH_AMERICA);
        Burnaby = new City("burnaby", 1, Continent.NORTH_AMERICA);

        London = new City("london", 5, Continent.EUROPE);
        France = new City("london", 4, Continent.EUROPE);
    }

    @Test
    public void testDestinationList() {
        testDestinationList = new DestinationList();
    }

    @Test
    public void testAddCity() {
        City Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        testDestinationList.addCity(Vancouver);

        assertTrue(testDestinationList.containsCity(Vancouver));
    }

    @Test
    public void testGetAllCities() {
        testDestinationList.addCity(Vancouver);
        testDestinationList.addCity(Richmond);
        testDestinationList.addCity(Burnaby);
        testDestinationList.addCity(London);
        testDestinationList.addCity(France);

        List<City> testAllCities = testDestinationList.getAllCities();

        assertEquals(5, testAllCities.size());
    }

    @Test
    public void testGetCitiesByRating() {
        testDestinationList.addCity(Vancouver);
        testDestinationList.addCity(Richmond);
        testDestinationList.addCity(Burnaby);
        testDestinationList.addCity(London);
        testDestinationList.addCity(France);

        List<City> testCitiesByRating = testDestinationList.getCitiesByRating(5);

        assertEquals(2, testCitiesByRating.size());
        assertTrue(testCitiesByRating.contains(Vancouver));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
        assertFalse(testCitiesByRating.contains(France));
    }


    @Test
    public void testGetCitiesByContinent() {
        testDestinationList.addCity(Vancouver);
        testDestinationList.addCity(Richmond);
        testDestinationList.addCity(Burnaby);
        testDestinationList.addCity(London);
        testDestinationList.addCity(France);

        List<City> testCitiesByRating = testDestinationList.getCitiesByContinent(Continent.EUROPE);

        assertEquals(2, testCitiesByRating.size());

        assertTrue(testCitiesByRating.contains(France));
        assertTrue(testCitiesByRating.contains(London));

        assertFalse(testCitiesByRating.contains(Vancouver));
        assertFalse(testCitiesByRating.contains(Richmond));
        assertFalse(testCitiesByRating.contains(Burnaby));
    }

    @Test
    public void testConvertContinentNum() {
        assertEquals(Continent.AFRICA, testDestinationList.convertContinentNum(1));
        assertEquals(Continent.ANTARCTICA, testDestinationList.convertContinentNum(2));
        assertEquals(Continent.ASIA, testDestinationList.convertContinentNum(3));
        assertEquals(Continent.EUROPE, testDestinationList.convertContinentNum(4));
        assertEquals(Continent.OCEANIA, testDestinationList.convertContinentNum(5));
        assertEquals(Continent.NORTH_AMERICA, testDestinationList.convertContinentNum(6));
        assertEquals(Continent.SOUTH_AMERICA, testDestinationList.convertContinentNum(7));
    }

//    @Test
//    void testPrintAllDestinationList() {
//        testDestinationList.addCity(Vancouver);
//        testDestinationList.addCity(Richmond);
//        testDestinationList.addCity(Burnaby);
//        testDestinationList.addCity(London);
//        testDestinationList.addCity(France);
//
//        assertEquals(5, testDestinationList.printAllDestinationList());
//    }
//
//    @Test
//    void testPrintDestinationListByContinent() {
//        testDestinationList.addCity(Vancouver);
//        testDestinationList.addCity(Richmond);
//        testDestinationList.addCity(Burnaby);
//        testDestinationList.addCity(London);
//        testDestinationList.addCity(France);
//
//        assertEquals(2, testCitiesByRating.size());
//    }
//
//    @Test
//    void testPrintDestinationListByRatings() {
//        testDestinationList.addCity(Vancouver);
//        testDestinationList.addCity(Richmond);
//        testDestinationList.addCity(Burnaby);
//        testDestinationList.addCity(London);
//        testDestinationList.addCity(France);
//
//        List<City> testCitiesByRating = testDestinationList.getCitiesByRating(5);
}

