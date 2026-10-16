package academy.tochkavhoda.competition.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Application {
    private int id;
    private String name;
    private String description;
    private List<String> directions;
    private int amount;
    private Participant owner;
    private Map<String, Integer> ratings;

    public Application(String name, String description, List<String> directions, int amount, Participant owner) {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.amount = amount;
        this.owner = owner;
        this.ratings = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDirections() {
        return directions;
    }

    public int getAmount() {
        return amount;
    }

    public Participant getOwner() {
        return owner;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (int rating : ratings.values()) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
