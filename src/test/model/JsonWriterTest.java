package model;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code referenced from JsonSerializationDemo project
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            DestinationList dl = new DestinationList("My destination list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDestinationList() {
        try {
            DestinationList dl = new DestinationList("My destination list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDestinationList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDestinationList.json");
            dl = reader.read();
            assertEquals("My destination list", dl.getName());
            assertEquals(0, dl.numCities());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDestinationList() {
        try {
            DestinationList dl = new DestinationList("My destination list");
            dl.addCity(new City("tokyo", 5, Continent.ASIA));
            dl.addCity(new City("perth", 4, Continent.OCEANIA));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDestinationList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDestinationList.json");
            dl = reader.read();
            assertEquals("My destination list", dl.getName());
            List<City> cities = dl.getAllCities();
            assertEquals(2, cities.size());
            checkCity("tokyo", 5, Continent.ASIA, cities.get(0));
            checkCity("perth", 4, Continent.OCEANIA, cities.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
