package model;

import model.city.City;
import model.city.Continent;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code referenced from JsonSerializationDemo project
public class JsonTest {

    protected void checkCity(String name, Integer rating, Continent continent, City city) {
        assertEquals(name, city.getName());
        assertEquals(rating, city.getRating());
        assertEquals(continent, city.getContinent());
    }
}
