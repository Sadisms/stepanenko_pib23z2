package academy.tochkavhoda.competition.exception;

public class CompetitionException extends Exception {
    private final CompetitionErrorCode errorCode;

    public CompetitionException(CompetitionErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public CompetitionErrorCode getErrorCode() {
        return errorCode;
    }
}
