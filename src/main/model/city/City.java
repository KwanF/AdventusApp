package model.city;

public class City {

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

}

