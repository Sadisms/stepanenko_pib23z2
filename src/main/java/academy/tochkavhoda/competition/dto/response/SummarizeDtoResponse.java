package academy.tochkavhoda.competition.dto.response;

import java.util.List;

public class SummarizeDtoResponse {
    private List<ApplicationDtoResponse> winners;
    private int remainingFund;

    public SummarizeDtoResponse(List<ApplicationDtoResponse> winners, int remainingFund) {
        this.winners = winners;
        this.remainingFund = remainingFund;
    }

    public List<ApplicationDtoResponse> getWinners() {
        return winners;
    }

    public int getRemainingFund() {
        return remainingFund;
    }
}
