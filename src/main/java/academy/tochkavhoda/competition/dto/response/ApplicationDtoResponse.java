package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class ApplicationDtoResponse {
    private int id;
    private String name;
    private String description;
    private List<String> directions;
    private int amount;
    private String firmName;
    private double averageRating;
    private int ratingsCount;

    public ApplicationDtoResponse(int id, String name, String description, List<String> directions, int amount,
                                  String firmName, double averageRating, int ratingsCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.amount = amount;
        this.firmName = firmName;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public int getId() {
        return id;
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

    public String getFirmName() {
        return firmName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }
}
