package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.request.LoginDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterParticipantDtoRequest;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.server.ServerResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUsers extends TestBase {

    @Test
    public void testRegisterParticipant() {
        RegisterParticipantDtoRequest request = new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Рога и копыта", "ivanov", "password1");
        ServerResponse response = server.registerParticipant(gson.toJson(request));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());
    }

    @Test
    public void testRegisterParticipantWrongData() {
        assertError(server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                null, "Иванов", "Фирма", "ivanov", "password1"))), "Имя не может быть пустым");
        assertError(server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                "Иван", "", "Фирма", "ivanov", "password1"))), "Фамилия не может быть пустой");
        assertError(server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "", "ivanov", "password1"))), "Название фирмы не может быть пустым");
        assertError(server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Фирма", "iv", "password1"))), "Логин должен содержать не менее 3 символов");
        assertError(server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Фирма", "ivanov", "12345"))), "Пароль должен содержать не менее 6 символов");
        assertError(server.registerParticipant("not a json"), "Неверный формат запроса");
    }

    @Test
    public void testRegisterDuplicateLogin() {
        RegisterParticipantDtoRequest request = new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Фирма", "ivanov", "password1");
        assertEquals(200, server.registerParticipant(gson.toJson(request)).getResponseCode());
        assertError(server.registerParticipant(gson.toJson(request)), "Этот логин уже используется");

        RegisterExpertDtoRequest expertRequest = new RegisterExpertDtoRequest(
                "Петр", "Петров", Arrays.asList("математика"), "ivanov", "password2");
        assertError(server.registerExpert(gson.toJson(expertRequest)), "Этот логин уже используется");
    }

    @Test
    public void testRegisterExpert() {
        RegisterExpertDtoRequest request = new RegisterExpertDtoRequest(
                "Петр", "Петров", Arrays.asList("математика", "информатика"), "petrov", "password1");
        ServerResponse response = server.registerExpert(gson.toJson(request));
        assertEquals(200, response.getResponseCode());
        assertEquals("{}", response.getResponseData());
    }

    @Test
    public void testRegisterExpertWrongDirections() {
        assertError(server.registerExpert(gson.toJson(new RegisterExpertDtoRequest(
                "Петр", "Петров", null, "petrov", "password1"))), "Список направлений не может быть пустым");
        assertError(server.registerExpert(gson.toJson(new RegisterExpertDtoRequest(
                "Петр", "Петров", Collections.emptyList(), "petrov", "password1"))), "Список направлений не может быть пустым");
    }

    @Test
    public void testLoginLogout() {
        String token = registerParticipant("ivanov", "password1");
        assertEquals(200, server.getMyApplications(token).getResponseCode());

        assertEquals(200, server.logout(token).getResponseCode());
        assertError(server.getMyApplications(token), "Недействительный токен");
        assertError(server.logout(token), "Недействительный токен");
        assertError(server.getMyApplications("bad token"), "Недействительный токен");

        String newToken = login("ivanov", "password1");
        assertNotEquals(token, newToken);
        assertEquals(200, server.getMyApplications(newToken).getResponseCode());
    }

    @Test
    public void testLoginWrongPassword() {
        registerParticipant("ivanov", "password1");
        assertError(server.login(gson.toJson(new LoginDtoRequest("ivanov", "wrong"))), "Неверный логин или пароль");
        assertError(server.login(gson.toJson(new LoginDtoRequest("nobody", "password1"))), "Неверный логин или пароль");
    }

    @Test
    public void testParticipantLeaveServer() {
        List<String> directions = Arrays.asList("математика");
        String participantToken = registerParticipant("ivanov", "password1");
        String expertToken = registerExpert("petrov", "password1", directions);
        addApplication(participantToken, "Заявка 1", directions, 100);
        addApplication(participantToken, "Заявка 2", directions, 200);

        ServerResponse response = server.getApplicationsByDirections(expertToken,
                "{\"directions\":[\"математика\"]}");
        assertEquals(2, gson.fromJson(response.getResponseData(), ApplicationsDtoResponse.class).getApplications().size());

        assertEquals(200, server.leaveServer(participantToken).getResponseCode());
        assertError(server.getMyApplications(participantToken), "Недействительный токен");
        assertError(server.login(gson.toJson(new LoginDtoRequest("ivanov", "password1"))), "Неверный логин или пароль");

        response = server.getApplicationsByDirections(expertToken, "{\"directions\":[\"математика\"]}");
        assertEquals(0, gson.fromJson(response.getResponseData(), ApplicationsDtoResponse.class).getApplications().size());

        assertEquals(200, server.registerParticipant(gson.toJson(new RegisterParticipantDtoRequest(
                "Иван", "Иванов", "Фирма", "ivanov", "password1"))).getResponseCode());
    }

    @Test
    public void testExpertLeaveServer() {
        List<String> directions = Arrays.asList("математика");
        String participantToken = registerParticipant("ivanov", "password1");
        String expertToken = registerExpert("petrov", "password1", directions);
        int id = addApplication(participantToken, "Заявка 1", directions, 100);
        assertEquals(200, server.rateApplication(expertToken, "{\"id\":" + id + ",\"rating\":5}").getResponseCode());

        ApplicationsDtoResponse apps = gson.fromJson(server.getMyApplications(participantToken).getResponseData(),
                ApplicationsDtoResponse.class);
        assertEquals(1, apps.getApplications().get(0).getRatingsCount());

        assertEquals(200, server.leaveServer(expertToken).getResponseCode());
        assertError(server.getRatedApplications(expertToken), "Недействительный токен");

        apps = gson.fromJson(server.getMyApplications(participantToken).getResponseData(), ApplicationsDtoResponse.class);
        assertEquals(0, apps.getApplications().get(0).getRatingsCount());
        assertEquals(0, apps.getApplications().get(0).getAverageRating());
    }
}
