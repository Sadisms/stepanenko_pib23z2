package academy.tochkavhoda.competition.exception;

public enum CompetitionErrorCode {
    WRONG_JSON("Неверный формат запроса"),
    WRONG_FIRST_NAME("Имя не может быть пустым"),
    WRONG_LAST_NAME("Фамилия не может быть пустой"),
    WRONG_FIRM_NAME("Название фирмы не может быть пустым"),
    WRONG_LOGIN("Логин должен содержать не менее 3 символов"),
    WRONG_PASSWORD("Пароль должен содержать не менее 6 символов"),
    WRONG_DIRECTIONS("Список направлений не может быть пустым"),
    WRONG_APPLICATION_NAME("Название заявки не может быть пустым"),
    WRONG_APPLICATION_DESCRIPTION("Описание заявки не может быть пустым"),
    WRONG_AMOUNT("Сумма гранта должна быть больше нуля"),
    WRONG_RATING("Оценка должна быть от 1 до 5"),
    WRONG_FUND("Фонд гранта должен быть больше нуля"),
    LOGIN_ALREADY_USED("Этот логин уже используется"),
    USER_NOT_FOUND("Неверный логин или пароль"),
    WRONG_TOKEN("Недействительный токен"),
    NOT_PARTICIPANT("Операция доступна только участникам"),
    NOT_EXPERT("Операция доступна только экспертам"),
    APPLICATION_NOT_FOUND("Заявка не найдена"),
    NOT_OWNER("Заявка принадлежит другому участнику"),
    NOT_EXPERT_DIRECTION("Эксперт не работает по этому направлению"),
    RATING_NOT_FOUND("Оценка не найдена"),
    COMPETITION_CLOSED("Конкурс завершен");

    private final String errorString;

    CompetitionErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
