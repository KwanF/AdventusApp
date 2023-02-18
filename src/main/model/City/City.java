package model.City;

public class City {

    private String name;
    private int rating;
    private Continent continent;

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

