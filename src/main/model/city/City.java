package model.city;

import org.json.JSONObject;
import persistence.Writable;

public class City implements Writable {

    private final String name;
    private final int rating;
    private final Continent continent;

    // REQUIRES: The name of the city has a non-zero length
    // EFFECTS: Creates a city with the given name, rating, and corresponding continent
    public City(String name, int rating, Continent continent) {
        this.name = name;
        this.rating = rating;
        this.continent = continent;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public Continent getContinent() {
        return continent;
    }

    // Code referenced from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("continent", continent);
        return json;
    }

}

