package model;

import model.city.City;
import model.city.Continent;
import ui.AdventusApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdventusAppTest {
    private List<City> testCities;
    private City Vancouver;
    private City Richmond;
    private City Burnaby;
    private City London;
    private City France;
    
    @BeforeEach
    void runBefore() {
        testCities = new ArrayList<>();
        Vancouver = new City("vancouver", 5, Continent.NORTH_AMERICA);
        Richmond = new City("richmond", 3, Continent.NORTH_AMERICA);
        Burnaby = new City("burnaby", 1, Continent.NORTH_AMERICA);

        London = new City("london", 5, Continent.EUROPE);
        France = new City("london", 4, Continent.EUROPE);
    }

    @Test
    void testDoAddCity() {
        testCities.add(Vancouver);

        assertTrue(testCities.contains(Vancouver));
        assertEquals(1, testCities.size());
    }

    @Test
    void testDoRemoveCity() {
        testCities.add(Vancouver);
        testCities.add(Richmond);
        assertEquals(2, testCities.size());

        testCities.remove(Richmond);
        assertEquals(1, testCities.size());
        assertTrue(testCities.contains(Vancouver));
        assertFalse(testCities.contains(Richmond));
    }

    @Test
    void testConstructor() {
        assertEquals("vancouver", Vancouver.getName());
        assertEquals(5, Vancouver.getRating());
        assertEquals(Continent.NORTH_AMERICA, Vancouver.getContinent());
    }

}
