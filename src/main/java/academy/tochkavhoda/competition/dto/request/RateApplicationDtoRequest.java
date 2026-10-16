package academy.tochkavhoda.competition.dto.request;

public class RateApplicationDtoRequest {
    private int id;
    private int rating;

    public RateApplicationDtoRequest(int id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }
}
