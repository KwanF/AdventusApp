package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import org.json.*;

// Code referenced from JsonSerializationDemo project
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads destination list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DestinationList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDestinationList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses destinationlist from JSON object and returns it
    private DestinationList parseDestinationList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        DestinationList dl = new DestinationList(name);
        addCities(dl, jsonObject);
        return dl;
    }

    // MODIFIES: dl
    // EFFECTS: parses cities from JSON object and adds them to destinationList
    private void addCities(DestinationList dl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cities");
        for (Object json : jsonArray) {
            JSONObject nextCities = (JSONObject) json;
            addCity(dl, nextCities);
        }
    }

    // MODIFIES: dl
    // EFFECTS: parses cities from JSON object and adds it to destinationList
    private void addCity(DestinationList dl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer rating = jsonObject.getInt("rating");
        Continent continent = Continent.valueOf(jsonObject.getString("continent"));
        City city = new City(name, rating, continent);
        dl.addCity(city);
    }
}
