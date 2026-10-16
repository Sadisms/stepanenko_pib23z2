package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class AddApplicationDtoRequest {
    private String name;
    private String description;
    private List<String> directions;
    private int amount;

    public AddApplicationDtoRequest(String name, String description, List<String> directions, int amount) {
        this.name = name;
        this.description = description;
        this.directions = directions;
        this.amount = amount;
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
}
