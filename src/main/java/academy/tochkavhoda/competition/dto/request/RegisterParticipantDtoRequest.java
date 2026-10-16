package academy.tochkavhoda.competition.dto.request;

public class RegisterParticipantDtoRequest {
    private String firstName;
    private String lastName;
    private String firmName;
    private String login;
    private String password;

    public RegisterParticipantDtoRequest(String firstName, String lastName, String firmName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.firmName = firmName;
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
