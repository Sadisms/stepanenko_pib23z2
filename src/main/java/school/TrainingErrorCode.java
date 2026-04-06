package school;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Wrong first name"),
    TRAINEE_WRONG_LASTNAME("Wrong last name"),
    TRAINEE_WRONG_RATING("Wrong rating"),
    TRAINEE_NOT_FOUND("Trainee not found"),
    DUPLICATE_TRAINEE("Duplicate trainee"),
    GROUP_WRONG_NAME("Wrong group name"),
    GROUP_WRONG_ROOM("Wrong group room"),
    GROUP_NOT_FOUND("Group not found"),
    DUPLICATE_GROUP_NAME("Duplicate group name"),
    SCHOOL_WRONG_NAME("Wrong school name"),
    EMPTY_TRAINEE_QUEUE("Trainee queue is empty"),
    DUPLICATE_PASSPORT("Duplicate passport"),
    PASSPORT_NOT_FOUND("Passport not found");

    private final String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
