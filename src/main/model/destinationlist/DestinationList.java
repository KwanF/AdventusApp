package model.destinationlist;

import model.city.City;
import model.city.Continent;

import java.util.ArrayList;
import java.util.List;

// Represents a list of destinations having a list of cities that the user has visited
public class DestinationList {
    private List<City> cities; // all cities added to the destination list

    // EFFECTS: Constructs a new destination list
    public DestinationList() {
        this.cities = new ArrayList<>();
    }

    // REQUIRES: A city
    // MODIFIES: this
    // EFFECTS: Adds a city to the list
    public void addCity(City city) {
        this.cities.add(city);
    }

    // REQUIRES: A city
    // MODIFIES:
    // EFFECTS: Returns true if the destination list contains the requested city, false otherwise
    public boolean containsCity(City city) {
        return (this.cities.contains(city));
    }

    // EFFECTS: Returns the cities that has the given rating
    public List<City> getAllCities() {
        List<City> allCities = new ArrayList<>();

        for (City c : this.cities) {
            allCities.add(c);
        }
        return allCities;
    }

    // EFFECTS: Returns the cities that has the given rating
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


    // EFFECTS: Prints out all cities in the destinations list
    public void printAllDestinationList() {

        System.out.println("Here's your travel destination list: \n");
        for (City c : this.cities) {
            System.out.println("'" + c.getName());
        }
    }

    // REQUIRES: An integer rating from 1-5
    // EFFECTS: Prints out the list of cities with the given rating
    public void printDestinationListByRatings(int rating) {

        System.out.println("Here's your travel destination list filtered by rating: \n");
        for (City c : this.cities) {
            if (c.getRating() == rating) {
                System.out.println("'" + c.getName());
            }
        }

    }


    // REQUIRES: A integer representing a continent
    // EFFECTS: Prints out the list of cities in the given continent
    public void printDestinationListByContinent(int continentNum) {
        Continent continent;
        continent = convertContinentNum(continentNum);

        System.out.println("Here's your travel destination list filtered by continent: \n");
        for (City c : this.cities) {
            if (c.getContinent().equals(continent)) {
                System.out.println("'" + c.getName());
            }
        }
    }

    // REQUIRES: An integer
    // EFFECTS: Returns a continent corresponding to the integer
    private Continent convertContinentNum(int continentNum) {
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
}
