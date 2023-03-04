package model.destinationlist;

import model.city.City;
import model.city.Continent;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of destinations having a list of cities that the user has visited
public class DestinationList implements Writable {
    private String name;
    private List<City> cities; // all cities added to the destination list

    // EFFECTS: Constructs a new destination list and empty list of cities
    public DestinationList(String name) {
        this.name = name;
        this.cities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    // MODIFIES: this
    // EFFECTS: adds a city to the list
    public void addCity(City city) {
        this.cities.add(city);
    }

    // MODIFIES: this
    // EFFECTS: removes the given city from the list
    public boolean removeCity(String cityToRemove) {
        City removeThisCity = null;

        for (City c : this.cities) {
            if (c.getName().equals(cityToRemove)) {
                removeThisCity = c;
            }
        }
        return this.cities.remove(removeThisCity);
    }

    // REQUIRES: A city
    // EFFECTS: Returns true if the destination list contains the requested city, false otherwise
    public boolean containsCity(City city) {
        return (this.cities.contains(city));
    }

    // EFFECTS: Returns all cities in the destination list
    public List<City> getAllCities() {
        List<City> allCities = new ArrayList<>();

        for (City c : this.cities) {
            allCities.add(c);
        }
        return allCities;
    }

    // EFFECTS: returns number of cities in this destination list
    public int numCities() {
        return cities.size();
    }

    // EFFECTS: Returns the cities that have the given rating
    public List<City> getCitiesByRating(int rating) {
        List<City> citiesByRating = new ArrayList<>();

        for (City c : this.cities) {
            if (c.getRating() == rating) {
                citiesByRating.add(c);
            }
        }
        return citiesByRating;
    }


    // EFFECTS: Returns the cities that is in the given continent
    public List<City> getCitiesByContinent(Continent continent) {
        List<City> citiesByContinent = new ArrayList<>();

        for (City c : this.cities) {
            if (c.getContinent() == continent) {
                citiesByContinent.add(c);
            }
        }
        return citiesByContinent;
    }

    // REQUIRES: An integer representing a continent
    // EFFECTS: Returns a continent corresponding to the integer
    public Continent convertContinentNum(int continentNum) {
        if (continentNum == 1) {
            return Continent.AFRICA;
        } else if (continentNum == 2) {
            return Continent.ANTARCTICA;
        } else if (continentNum == 3) {
            return Continent.ASIA;
        } else if (continentNum == 4) {
            return Continent.EUROPE;
        } else if (continentNum == 5) {
            return Continent.OCEANIA;
        } else if (continentNum == 6) {
            return Continent.NORTH_AMERICA;
        } else {
            return Continent.SOUTH_AMERICA;

        }
    }

    // Code referenced from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cities", citiesToJson());
        return json;
    }

    // Code referenced from JsonSerializationDemo
    // EFFECTS: returns cities in this DestinationList as a JSON array
    private JSONArray citiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (City c : cities) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
