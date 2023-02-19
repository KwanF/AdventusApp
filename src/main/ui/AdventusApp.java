package ui;


import model.City.City;
import model.City.Continent;
import model.DestinationList.DestinationList;

import java.util.Scanner;

// Adventus travel planner application
public class AdventusApp {
    private DestinationList list1;
    private Scanner input;

    private Continent continent;
    private City newCity;

    private String criteria;
    private String name;

    private int rating;
    private int continentNum;
    private int continentRequested;
    private int ratingRequested;


    //EFFECTS: runs the Adventus application
    public AdventusApp() {
        runAdventus();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAdventus() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCity();
        } else if (command.equals("v")) {
            viewCities();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a destination list
    private void init() {
        list1 = new DestinationList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a city");
        System.out.println("\tv -> View cities saved");
        System.out.println("\tq -> quit\n");
    }

    // EFFECTS: Adds a city to the list
    private void doAddCity() {
        System.out.println("Enter the name of the city you want to visit:");
        input.nextLine();
        name = input.nextLine();

        System.out.print("\nEnter a rating for the city:");
        displayRatingsMenu();
        rating = input.nextInt();

        System.out.print("\nEnter the continent for the city");
        displayContinentMenu();
        continentNum = input.nextInt();
        continent = convertContinentNum(continentNum);

        newCity = new City(name, rating, continent);
        list1.addCity(newCity);
        list1.printAllDestinationList();
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

    // EFFECTS: View the cities based on the user criteria
    private void viewCities() {
        System.out.println("\n");
        criteria = selectCriteria();

        if (criteria.equals("ratings")) {
            displayRatingsMenu();
            ratingRequested = input.nextInt();
            list1.printDestinationListByRatings(ratingRequested);

        } else if (criteria.equals("continent")) {
            displayContinentMenu();
            continentRequested = input.nextInt();
            continent = convertContinentNum(continentRequested);
            list1.printDestinationListByContinent(continentRequested);
        } else {
            list1.printAllDestinationList();
        }

    }

    // EFFECTS: prompts user to filter list based on rating or continent, and returns it
    private String selectCriteria() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("r") || selection.equals("c") || (selection.equals("a")))) {
            System.out.println("press a to show all items");
            System.out.println("press r to filter by ratings");
            System.out.println("press c to filter by continent");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            return "all";
        } else if (selection.equals("r")) {
            return "ratings";
        } else {
            return "continent";
        }
    }


    // EFFECTS: Displays a menu of ratings that users can assign to a city
    private void displayRatingsMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> 1 star");
        System.out.println("\t2 -> 2 star");
        System.out.println("\t3 -> 3 star");
        System.out.println("\t4 -> 4 star");
        System.out.println("\t5 -> 5 star");
    }

    // EFFECTS: Displays a menu of continents that users can assign to a city
    private void displayContinentMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> AFRICA");
        System.out.println("\t2 -> ANTARCTICA");
        System.out.println("\t3 -> ASIA");
        System.out.println("\t4 -> EUROPE");
        System.out.println("\t5 -> OCEANIA");
        System.out.println("\t6 -> NORTH_AMERICA");
        System.out.println("\t7 -> SOUTH_AMERICA");
    }


}






