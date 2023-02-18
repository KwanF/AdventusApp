package ui;


import model.DestinationList.DestinationList;

import java.util.LinkedList;
import java.util.Scanner;

// Adventus travel planner application
public class AdventusApp {
    private DestinationList list1;
    private Scanner input;
    private String criteria;

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
    // EFFECTS: initializes a list of cities
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
        System.out.println("\tq -> quit");
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

    // EFFECTS: View the cities based on the user criteria
    private void viewCities() {
        System.out.println("\n");
        criteria = selectCriteria();

        if (critieria.equals("ratings")) {
            displayRatingsMenu();
            System.out.println("Cannot transfer negative amount...\n");
        } else (critieria.equals("continent")) {
            displayContinentMenu();
            System.out.println("Insufficient balance on source account...\n");
        }
    }

    // EFFECTS: prompts user to filter list based on rating or continent, and returns it
    private String selectCriteria() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("r") || selection.equals("c"))) {
            System.out.println("r for ratings");
            System.out.println("c for continent");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("r")) {
            return "ratings";
        } else {
            return "continent";
        }
    }

    private void displayRatingsMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> 1 star");
        System.out.println("\t2 -> 2 star");
        System.out.println("\t3 -> 3 star");
        System.out.println("\t4 -> 4 star");
        System.out.println("\t5 -> 5 star");
        System.out.println("\tq -> quit");
    }

    private void displayContinentMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> AFRICA");
        System.out.println("\t2 -> ANTARCTICA");
        System.out.println("\t3 -> ASIA");
        System.out.println("\t4 -> EUROPE");
        System.out.println("\t5 -> OCEANIA");
        System.out.println("\t6 -> NORTH_AMERICA");
        System.out.println("\t7 -> SOUTH_AMERICA");
        System.out.println("\tq -> quit");
    }


}






