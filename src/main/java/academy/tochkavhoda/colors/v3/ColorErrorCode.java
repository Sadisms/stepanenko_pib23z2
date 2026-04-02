package academy.tochkavhoda.colors.v3;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("Wrong color string"),
    NULL_COLOR("Color must not be null");

    private final String errorString;

    ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
