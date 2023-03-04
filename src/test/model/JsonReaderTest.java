package model;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code referenced from JsonSerializationDemo project
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DestinationList dl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDestinationList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDestinationList.json");
        try {
            DestinationList dl = reader.read();
            assertEquals("My destination list", dl.getName());
            assertEquals(0, dl.numCities());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDestinationList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDestinationList.json");
        try {
            DestinationList dl = reader.read();
            assertEquals("My destination list", dl.getName());
            List<City> cities = dl.getAllCities();
            assertEquals(2, cities.size());
            checkCity("vancouver", 5, Continent.NORTH_AMERICA, cities.get(0));
            checkCity("london", 4, Continent.EUROPE, cities.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
