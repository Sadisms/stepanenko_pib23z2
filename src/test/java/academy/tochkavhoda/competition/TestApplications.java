package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.DirectionsDtoRequest;
import academy.tochkavhoda.competition.dto.request.RateApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.server.ServerResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplications extends TestBase {

    private List<ApplicationDtoResponse> applications(ServerResponse response) {
        assertEquals(200, response.getResponseCode());
        return gson.fromJson(response.getResponseData(), ApplicationsDtoResponse.class).getApplications();
    }

    private ServerResponse rate(String token, int id, int rating) {
        return server.rateApplication(token, gson.toJson(new RateApplicationDtoRequest(id, rating)));
    }

    private ServerResponse byDirections(String token, String... directions) {
        return server.getApplicationsByDirections(token, gson.toJson(new DirectionsDtoRequest(Arrays.asList(directions))));
    }

    @Test
    public void testAddApplication() {
        String token = registerParticipant("ivanov", "password1");
        int id1 = addApplication(token, "Заявка 1", Arrays.asList("математика", "информатика"), 1000);
        int id2 = addApplication(token, "Заявка 2", Arrays.asList("физика"), 2000);
        assertNotEquals(id1, id2);

        List<ApplicationDtoResponse> apps = applications(server.getMyApplications(token));
        assertEquals(2, apps.size());
        ApplicationDtoResponse first = null;
        for (ApplicationDtoResponse app : apps) {
            if (app.getId() == id1) {
                first = app;
            }
        }
        assertEquals("Заявка 1", first.getName());
        assertEquals("Описание Заявка 1", first.getDescription());
        assertEquals(Arrays.asList("математика", "информатика"), first.getDirections());
        assertEquals(1000, first.getAmount());
        assertEquals("Фирма ivanov", first.getFirmName());
        assertEquals(0, first.getRatingsCount());
    }

    @Test
    public void testAddApplicationWrongData() {
        String token = registerParticipant("ivanov", "password1");
        List<String> directions = Arrays.asList("математика");
        assertError(server.addApplication(token, gson.toJson(new AddApplicationDtoRequest(
                "", "описание", directions, 100))), "Название заявки не может быть пустым");
        assertError(server.addApplication(token, gson.toJson(new AddApplicationDtoRequest(
                "Заявка", null, directions, 100))), "Описание заявки не может быть пустым");
        assertError(server.addApplication(token, gson.toJson(new AddApplicationDtoRequest(
                "Заявка", "описание", Collections.emptyList(), 100))), "Список направлений не может быть пустым");
        assertError(server.addApplication(token, gson.toJson(new AddApplicationDtoRequest(
                "Заявка", "описание", directions, 0))), "Сумма гранта должна быть больше нуля");
        assertError(server.addApplication(token, "{bad json"), "Неверный формат запроса");
        assertEquals(0, applications(server.getMyApplications(token)).size());
    }

    @Test
    public void testExpertCannotAddApplication() {
        String token = registerExpert("petrov", "password1", Arrays.asList("математика"));
        assertError(server.addApplication(token, gson.toJson(new AddApplicationDtoRequest(
                "Заявка", "описание", Arrays.asList("математика"), 100))),
                "Операция доступна только участникам");
        assertError(server.getMyApplications(token), "Операция доступна только участникам");
    }

    @Test
    public void testCancelApplication() {
        String token = registerParticipant("ivanov", "password1");
        int id1 = addApplication(token, "Заявка 1", Arrays.asList("математика"), 100);
        int id2 = addApplication(token, "Заявка 2", Arrays.asList("математика"), 200);

        ServerResponse response = server.cancelApplication(token, gson.toJson(new ApplicationIdDtoRequest(id1)));
        assertEquals(200, response.getResponseCode());

        List<ApplicationDtoResponse> apps = applications(server.getMyApplications(token));
        assertEquals(1, apps.size());
        assertEquals(id2, apps.get(0).getId());

        assertError(server.cancelApplication(token, gson.toJson(new ApplicationIdDtoRequest(id1))), "Заявка не найдена");
    }

    @Test
    public void testCancelOtherParticipantApplication() {
        String token1 = registerParticipant("ivanov", "password1");
        String token2 = registerParticipant("sidorov", "password1");
        int id = addApplication(token1, "Заявка 1", Arrays.asList("математика"), 100);
        assertError(server.cancelApplication(token2, gson.toJson(new ApplicationIdDtoRequest(id))),
                "Заявка принадлежит другому участнику");
        assertEquals(1, applications(server.getMyApplications(token1)).size());
    }

    @Test
    public void testGetApplicationsByDirections() {
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", Arrays.asList("математика", "информатика"));
        int id1 = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);
        int id2 = addApplication(participant, "Заявка 2", Arrays.asList("математика", "информатика"), 200);
        int id3 = addApplication(participant, "Заявка 3", Arrays.asList("информатика"), 300);
        addApplication(participant, "Заявка 4", Arrays.asList("физика"), 400);

        List<ApplicationDtoResponse> apps = applications(byDirections(expert, "математика"));
        assertEquals(2, apps.size());
        assertTrue(containsId(apps, id1));
        assertTrue(containsId(apps, id2));

        apps = applications(byDirections(expert, "информатика"));
        assertEquals(2, apps.size());
        assertTrue(containsId(apps, id2));
        assertTrue(containsId(apps, id3));

        apps = applications(byDirections(expert, "математика", "информатика"));
        assertEquals(3, apps.size());

        assertError(byDirections(expert, "физика"), "Эксперт не работает по этому направлению");
        assertError(byDirections(expert), "Список направлений не может быть пустым");
        assertError(byDirections(participant, "математика"), "Операция доступна только экспертам");
    }

    @Test
    public void testRateApplication() {
        String participant = registerParticipant("ivanov", "password1");
        String expert1 = registerExpert("petrov", "password1", Arrays.asList("математика"));
        String expert2 = registerExpert("sidorov", "password1", Arrays.asList("математика"));
        int id = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);

        assertEquals(200, rate(expert1, id, 5).getResponseCode());
        assertEquals(200, rate(expert2, id, 2).getResponseCode());

        ApplicationDtoResponse app = applications(server.getMyApplications(participant)).get(0);
        assertEquals(2, app.getRatingsCount());
        assertEquals(3.5, app.getAverageRating());

        assertEquals(200, rate(expert2, id, 4).getResponseCode());
        app = applications(server.getMyApplications(participant)).get(0);
        assertEquals(2, app.getRatingsCount());
        assertEquals(4.5, app.getAverageRating());
    }

    @Test
    public void testRateApplicationWrongData() {
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", Arrays.asList("математика"));
        int id = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);
        int other = addApplication(participant, "Заявка 2", Arrays.asList("физика"), 100);

        assertError(rate(expert, id, 0), "Оценка должна быть от 1 до 5");
        assertError(rate(expert, id, 6), "Оценка должна быть от 1 до 5");
        assertError(rate(expert, 9999, 3), "Заявка не найдена");
        assertError(rate(expert, other, 3), "Эксперт не работает по этому направлению");
        assertError(rate(participant, id, 3), "Операция доступна только экспертам");
    }

    @Test
    public void testDeleteRating() {
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", Arrays.asList("математика"));
        int id = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);

        assertEquals(200, rate(expert, id, 3).getResponseCode());
        ServerResponse response = server.deleteRating(expert, gson.toJson(new ApplicationIdDtoRequest(id)));
        assertEquals(200, response.getResponseCode());

        ApplicationDtoResponse app = applications(server.getMyApplications(participant)).get(0);
        assertEquals(0, app.getRatingsCount());

        assertError(server.deleteRating(expert, gson.toJson(new ApplicationIdDtoRequest(id))), "Оценка не найдена");
        assertError(server.deleteRating(expert, gson.toJson(new ApplicationIdDtoRequest(9999))), "Заявка не найдена");
    }

    @Test
    public void testGetRatedApplications() {
        String participant = registerParticipant("ivanov", "password1");
        String expert1 = registerExpert("petrov", "password1", Arrays.asList("математика"));
        String expert2 = registerExpert("sidorov", "password1", Arrays.asList("математика"));
        int id1 = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);
        int id2 = addApplication(participant, "Заявка 2", Arrays.asList("математика"), 200);
        int id3 = addApplication(participant, "Заявка 3", Arrays.asList("математика"), 300);

        rate(expert1, id1, 5);
        rate(expert1, id3, 4);
        rate(expert2, id2, 3);

        List<ApplicationDtoResponse> apps = applications(server.getRatedApplications(expert1));
        assertEquals(2, apps.size());
        assertTrue(containsId(apps, id1));
        assertTrue(containsId(apps, id3));

        apps = applications(server.getRatedApplications(expert2));
        assertEquals(1, apps.size());
        assertEquals(id2, apps.get(0).getId());

        server.deleteRating(expert1, gson.toJson(new ApplicationIdDtoRequest(id1)));
        apps = applications(server.getRatedApplications(expert1));
        assertEquals(1, apps.size());
        assertEquals(id3, apps.get(0).getId());

        assertError(server.getRatedApplications(participant), "Операция доступна только экспертам");
    }

    @Test
    public void testCancelRatedApplication() {
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", Arrays.asList("математика"));
        int id = addApplication(participant, "Заявка 1", Arrays.asList("математика"), 100);
        rate(expert, id, 5);
        assertEquals(1, applications(server.getRatedApplications(expert)).size());

        server.cancelApplication(participant, gson.toJson(new ApplicationIdDtoRequest(id)));
        assertEquals(0, applications(server.getRatedApplications(expert)).size());
        assertEquals(0, applications(byDirections(expert, "математика")).size());
        assertError(rate(expert, id, 5), "Заявка не найдена");
    }
}
