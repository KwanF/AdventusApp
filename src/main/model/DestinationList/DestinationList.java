package model.DestinationList;

import model.City.City;

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
    public void printDestinationListByContinent(int continent) {

        System.out.println("Here's your travel destination list filtered by continent: \n");
        for (City c : this.cities) {
            if (c.getContinent().equals(continent)) {
                System.out.println("'" + c.getName());
            }
        }
    }
}
