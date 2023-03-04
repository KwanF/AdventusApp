package ui;

import model.city.City;
import model.city.Continent;
import model.destinationlist.DestinationList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Adventus city travelled tracker
public class AdventusApp {
    private DestinationList mainList;
    private Scanner input;

    private Continent continent;
    private City newCity;

    private String criteria;
    private String name;

    private int rating;
    private int continentNum;
    private int continentRequested;
    private int ratingRequested;

    private static final String JSON_STORE = "./data/destinationlist.json";
    private DestinationList destinationList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: constructs and runs the Adventus application
    public AdventusApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        destinationList = new DestinationList("Kwan's destination list");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAdventus();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Code based on the TellerApp provided in the CPSC 210 course
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
    // Code based on the TellerApp provided in the CPSC 210 course
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCity();
        } else if (command.equals("v")) {
            viewCities();
        } else if (command.equals("r")) {
            doRemoveCity();
        } else if (command.equals("s")) {
            saveDestinationList();
        } else if (command.equals("l")) {
            loadDestinationList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a destination list
    // Code based on the TellerApp provided in the CPSC 210 course
    private void init() {
        mainList = new DestinationList("Kwan's Destination List");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    // Code based on the TellerApp provided in the CPSC 210 course
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a city");
        System.out.println("\tv -> View cities saved");
        System.out.println("\tr -> Remove a saved city");
        System.out.println("\ts -> Save destination list to file");
        System.out.println("\tl -> Load destination list from file");
        System.out.println("\tq -> quit\n");
    }

    // REQUIRES: The city to be added does not exist already in the list
    // EFFECTS: Adds a city to the list
    private void doAddCity() {
        System.out.println("Enter the name of the city you have visited:");
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
        mainList.addCity(newCity);
        for (City c : mainList.getAllCities()) {
            System.out.println("'" + c.getName());
        }
    }

    // REQUIRES: The requested city to be removed needs to have already been added previously
    // EFFECTS: Removes a city from the list
    private void doRemoveCity() {
        System.out.println("\nEnter the name of the city you want to remove:");
        input.nextLine();
        name = input.nextLine();

        mainList.removeCity(name);
        System.out.println("\nRemoved: " + name);
        System.out.println("\nYour new list:");

        for (City c : mainList.getAllCities()) {
            System.out.println("'" + c.getName());
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

    // EFFECTS: View the cities based on the user criteria
    private void viewCities() {
        System.out.println("\n");
        criteria = selectCriteria();

        if (criteria.equals("ratings")) {
            displayRatingsMenu();
            ratingRequested = input.nextInt();
            List<City> citiesByRating = mainList.getCitiesByRating(ratingRequested);

            for (City c : citiesByRating) {
                System.out.println("'" + c.getName());
            }

        } else if (criteria.equals("continent")) {
            displayContinentMenu();
            continentRequested = input.nextInt();
            continent = convertContinentNum(continentRequested);
            List<City> citiesByContinent = mainList.getCitiesByContinent(continent);

            for (City c : citiesByContinent) {
                System.out.println("'" + c.getName());
            }
        } else {
            for (City c : mainList.getAllCities()) {
                System.out.println("'" + c.getName());
            }
        }

    }

    // EFFECTS: prompts user to filter list based on rating or continent, and returns it
    // Code based on the TellerApp provided in the CPSC 210 course
    private String selectCriteria() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("r") || selection.equals("c") || (selection.equals("a")))) {
            System.out.println("press 'a' to show all items");
            System.out.println("press 'r' to filter by ratings");
            System.out.println("press 'c' to filter by continent");
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

    // EFFECTS: saves the workroom to file
    private void saveDestinationList() {
        try {
            jsonWriter.open();
            jsonWriter.write(destinationList);
            jsonWriter.close();
            System.out.println("Saved " + destinationList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadDestinationList() {
        try {
            destinationList = jsonReader.read();
            System.out.println("Loaded " + destinationList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}






