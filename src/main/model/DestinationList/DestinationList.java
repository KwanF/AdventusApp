package model.DestinationList;

import model.City.City;

import java.util.ArrayList;
import java.util.List;

// Represents a list of destinations having a list of cities that the user has visited
public class DestinationList {
    private List<City> cities; // all cities added to the destination list

//    private List<City> oneStar; // cities with 1 star rating
//    private List<City> twoStar; // cities with 2 star rating
//    private List<City> threeStar; // cities with 3 star rating
//    private List<City> fourStar; // cities with 4 star rating
//    private List<City> fiveStar; // cities with 5 star rating
//
//    private List<City> africaList; // cities in Africa
//    private List<City> antarcticaList; // cities in Antarctica
//    private List<City> asiaList; // cities in Asia
//    private List<City> europeList; // cities in Europe
//    private List<City> oceaniaList; // cities in Oceania
//    private List<City> northAmericaList; // cities in North America
//    private List<City> southAmericaList; // cities in South America


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public DestinationList() {
        this.cities = new ArrayList<>();
//        oneStar = new LinkedList<>();
//        twoStar = new LinkedList<>();
//        threeStar = new LinkedList<>();
//        fourStar = new LinkedList<>();
//        fiveStar = new LinkedList<>();
//        africaList = new LinkedList<>();
//        antarcticaList = new LinkedList<>();
//        asiaList = new LinkedList<>();
//        europeList = new LinkedList<>();
//        oceaniaList = new LinkedList<>();
//        northAmericaList = new LinkedList<>();
//        southAmericaList = new LinkedList<>();
    }


//    // REQUIRES:
//    // MODIFIES:
//    // EFFECTS:
//    public void addToRatingList(City newCity) {
//
//        if (newCity.getRating() == 1) {
//            oneStar.add(newCity);
//        } else if (newCity.getRating() == 2) {
//            twoStar.add(newCity);
//        } else if (newCity.getRating() == 3) {
//            threeStar.add(newCity);
//        } else if (newCity.getRating() == 4) {
//            fourStar.add(newCity);
//        } else if (newCity.getRating() == 5) {
//            fiveStar.add(newCity);
//        }
//    }
//
//    // REQUIRES:
//    // MODIFIES:
//    // EFFECTS:
//    public void addToContinentList(City newCity) {
//
//        if (newCity.getContinent().equals(AFRICA)) {
//            africaList.add(newCity);
//        } else if (newCity.getContinent().equals(ANTARCTICA)) {
//            antarcticaList.add(newCity);
//        } else if (newCity.getContinent().equals(ASIA)) {
//            asiaList.add(newCity);
//        } else if (newCity.getContinent().equals(EUROPE)) {
//            europeList.add(newCity);
//        } else if (newCity.getContinent().equals(OCEANIA)) {
//            oceaniaList.add(newCity);
//        } else if (newCity.getContinent().equals(NORTH_AMERICA)) {
//            northAmericaList.add(newCity);
//        } else if (newCity.getContinent().equals(SOUTH_AMERICA)) {
//            southAmericaList.add(newCity);
//        }
//    }

    // getters
    public List<City> getCities() {
        return cities;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Adds a city to the list
    private void doAddCity() {
        System.out.print("Enter city to visit:");
        String city = input.nextLine();
        list1.getCities().add(city);

        list1.printAllDestinationList();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: Prints out all cities
    public void printAllDestinationList() {

        System.out.println("Here's your travel destination list: \n");
        for (City c : this.cities) {
            System.out.println("'" + c.getName());
        }
    }

    // REQUIRES: An integer rating from 1-5
    // MODIFIES:
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
    // MODIFIES:
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
