package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.LoginDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterParticipantDtoRequest;
import academy.tochkavhoda.competition.dto.response.AddApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ErrorDtoResponse;
import academy.tochkavhoda.competition.dto.response.LoginDtoResponse;
import academy.tochkavhoda.competition.server.Server;
import academy.tochkavhoda.competition.server.ServerResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class TestBase {
    protected static final Gson gson = new Gson();
    protected Server server;

    @BeforeEach
    public void setUp() {
        server = new Server();
        server.clear();
    }

    protected String registerParticipant(String login, String password) {
        RegisterParticipantDtoRequest request = new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Фирма " + login, login, password);
        ServerResponse response = server.registerParticipant(gson.toJson(request));
        assertEquals(200, response.getResponseCode());
        return login(login, password);
    }

    protected String registerExpert(String login, String password, List<String> directions) {
        RegisterExpertDtoRequest request = new RegisterExpertDtoRequest(
                "Петр", "Петров", directions, login, password);
        ServerResponse response = server.registerExpert(gson.toJson(request));
        assertEquals(200, response.getResponseCode());
        return login(login, password);
    }

    protected String login(String login, String password) {
        ServerResponse response = server.login(gson.toJson(new LoginDtoRequest(login, password)));
        assertEquals(200, response.getResponseCode());
        LoginDtoResponse loginResponse = gson.fromJson(response.getResponseData(), LoginDtoResponse.class);
        assertNotNull(loginResponse.getToken());
        return loginResponse.getToken();
    }

    protected int addApplication(String token, String name, List<String> directions, int amount) {
        AddApplicationDtoRequest request = new AddApplicationDtoRequest(name, "Описание " + name, directions, amount);
        ServerResponse response = server.addApplication(token, gson.toJson(request));
        assertEquals(200, response.getResponseCode());
        return gson.fromJson(response.getResponseData(), AddApplicationDtoResponse.class).getId();
    }

    protected boolean containsId(List<ApplicationDtoResponse> applications, int id) {
        for (ApplicationDtoResponse application : applications) {
            if (application.getId() == id) {
                return true;
            }
        }
        return false;
    }

    protected void assertError(ServerResponse response, String expectedError) {
        assertEquals(400, response.getResponseCode());
        ErrorDtoResponse error = gson.fromJson(response.getResponseData(), ErrorDtoResponse.class);
        assertEquals(expectedError, error.getError());
    }
}
