package academy.tochkavhoda.competition.dto.request;

public class SummarizeDtoRequest {
    private int fund;
    private double threshold;

    public SummarizeDtoRequest(int fund, double threshold) {
        this.fund = fund;
        this.threshold = threshold;
    }

    public int getFund() {
        return fund;
    }

    public double getThreshold() {
        return threshold;
    }
}
