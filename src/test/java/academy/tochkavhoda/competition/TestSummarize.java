package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.RateApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.SummarizeDtoRequest;
import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.SummarizeDtoResponse;
import academy.tochkavhoda.competition.server.ServerResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSummarize extends TestBase {

    private SummarizeDtoResponse summarize(int fund, double threshold) {
        ServerResponse response = server.summarize(gson.toJson(new SummarizeDtoRequest(fund, threshold)));
        assertEquals(200, response.getResponseCode());
        return gson.fromJson(response.getResponseData(), SummarizeDtoResponse.class);
    }

    private void rate(String token, int id, int rating) {
        ServerResponse response = server.rateApplication(token, gson.toJson(new RateApplicationDtoRequest(id, rating)));
        assertEquals(200, response.getResponseCode());
    }

    @Test
    public void testSummarizeByRating() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        String expert1 = registerExpert("petrov", "password1", directions);
        String expert2 = registerExpert("sidorov", "password1", directions);

        int id1 = addApplication(participant, "Заявка 1", directions, 300);
        int id2 = addApplication(participant, "Заявка 2", directions, 300);
        int id3 = addApplication(participant, "Заявка 3", directions, 300);

        rate(expert1, id1, 3);
        rate(expert2, id1, 3);
        rate(expert1, id2, 5);
        rate(expert2, id2, 5);
        rate(expert1, id3, 4);
        rate(expert2, id3, 5);

        SummarizeDtoResponse result = summarize(1000, 0);
        List<ApplicationDtoResponse> winners = result.getWinners();
        assertEquals(3, winners.size());
        assertEquals(id2, winners.get(0).getId());
        assertEquals(id3, winners.get(1).getId());
        assertEquals(id1, winners.get(2).getId());
        assertEquals(100, result.getRemainingFund());
    }

    @Test
    public void testSummarizeSkipTooExpensive() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", directions);

        int id1 = addApplication(participant, "Заявка 1", directions, 800);
        int id2 = addApplication(participant, "Заявка 2", directions, 500);
        int id3 = addApplication(participant, "Заявка 3", directions, 150);

        rate(expert, id1, 5);
        rate(expert, id2, 4);
        rate(expert, id3, 3);

        SummarizeDtoResponse result = summarize(1000, 0);
        List<ApplicationDtoResponse> winners = result.getWinners();
        assertEquals(2, winners.size());
        assertEquals(id1, winners.get(0).getId());
        assertEquals(id3, winners.get(1).getId());
        assertEquals(50, result.getRemainingFund());
    }

    @Test
    public void testSummarizeSameRatingOrderedByAmount() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", directions);

        int id1 = addApplication(participant, "Заявка 1", directions, 500);
        int id2 = addApplication(participant, "Заявка 2", directions, 100);
        int id3 = addApplication(participant, "Заявка 3", directions, 300);

        rate(expert, id1, 4);
        rate(expert, id2, 4);
        rate(expert, id3, 4);

        SummarizeDtoResponse result = summarize(10000, 0);
        List<ApplicationDtoResponse> winners = result.getWinners();
        assertEquals(3, winners.size());
        assertEquals(id2, winners.get(0).getId());
        assertEquals(id3, winners.get(1).getId());
        assertEquals(id1, winners.get(2).getId());
        assertEquals(9100, result.getRemainingFund());
    }

    @Test
    public void testSummarizeThreshold() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", directions);

        int id1 = addApplication(participant, "Заявка 1", directions, 100);
        int id2 = addApplication(participant, "Заявка 2", directions, 100);
        int id3 = addApplication(participant, "Заявка 3", directions, 100);
        addApplication(participant, "Заявка 4", directions, 100);

        rate(expert, id1, 5);
        rate(expert, id2, 3);
        rate(expert, id3, 2);

        SummarizeDtoResponse result = summarize(1000, 3);
        List<ApplicationDtoResponse> winners = result.getWinners();
        assertEquals(1, winners.size());
        assertEquals(id1, winners.get(0).getId());
        assertEquals(900, result.getRemainingFund());
    }

    @Test
    public void testSummarizeNoRatings() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        addApplication(participant, "Заявка 1", directions, 100);

        SummarizeDtoResponse result = summarize(1000, 0);
        assertEquals(0, result.getWinners().size());
        assertEquals(1000, result.getRemainingFund());
    }

    @Test
    public void testSummarizeWrongFund() {
        assertError(server.summarize(gson.toJson(new SummarizeDtoRequest(0, 0))), "Фонд гранта должен быть больше нуля");
        assertError(server.summarize("bad"), "Неверный формат запроса");
    }

    @Test
    public void testAfterSummarize() {
        List<String> directions = Arrays.asList("математика");
        String participant = registerParticipant("ivanov", "password1");
        String expert = registerExpert("petrov", "password1", directions);
        int id = addApplication(participant, "Заявка 1", directions, 100);
        rate(expert, id, 5);

        summarize(1000, 0);

        assertError(server.addApplication(participant, gson.toJson(new AddApplicationDtoRequest(
                "Заявка 2", "описание", directions, 100))), "Конкурс завершен");
        assertError(server.rateApplication(expert, gson.toJson(new RateApplicationDtoRequest(id, 4))), "Конкурс завершен");
        assertError(server.summarize(gson.toJson(new SummarizeDtoRequest(1000, 0))), "Конкурс завершен");

        assertEquals(200, server.getMyApplications(participant).getResponseCode());
    }

    @Test
    public void testScenario() {
        String p1 = registerParticipant("ivanov", "password1");
        String p2 = registerParticipant("sidorov", "password1");
        String math = registerExpert("expert1", "password1", Arrays.asList("математика"));
        String it = registerExpert("expert2", "password1", Arrays.asList("информатика"));
        String both = registerExpert("expert3", "password1", Arrays.asList("математика", "информатика"));

        int a1 = addApplication(p1, "Алгебра", Arrays.asList("математика"), 400);
        int a2 = addApplication(p1, "Нейросети", Arrays.asList("математика", "информатика"), 600);
        int a3 = addApplication(p2, "Сайт", Arrays.asList("информатика"), 200);
        int a4 = addApplication(p2, "Геометрия", Arrays.asList("математика"), 300);

        rate(math, a1, 5);
        rate(both, a1, 5);
        rate(math, a2, 4);
        rate(it, a2, 5);
        rate(both, a2, 3);
        rate(it, a3, 4);
        rate(both, a3, 4);
        rate(math, a4, 1);

        assertEquals(200, server.cancelApplication(p2, gson.toJson(new ApplicationIdDtoRequest(a4))).getResponseCode());
        assertEquals(200, server.logout(math).getResponseCode());

        SummarizeDtoResponse result = summarize(700, 2);
        List<ApplicationDtoResponse> winners = result.getWinners();
        assertEquals(2, winners.size());
        assertEquals(a1, winners.get(0).getId());
        assertEquals(a3, winners.get(1).getId());
        assertEquals(100, result.getRemainingFund());
        assertFalse(containsId(winners, a2));
    }
}
