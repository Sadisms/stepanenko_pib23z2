package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.CompetitionDaoImpl;
import academy.tochkavhoda.competition.dto.request.SummarizeDtoRequest;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.dto.response.SummarizeDtoResponse;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.server.ServerResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompetitionService extends BaseService {
    private ApplicationDao applicationDao = new ApplicationDaoImpl();
    private CompetitionDao competitionDao = new CompetitionDaoImpl();

    public ServerResponse summarize(String json) {
        try {
            SummarizeDtoRequest request = parse(json, SummarizeDtoRequest.class);
            if (request.getFund() <= 0) {
                throw new CompetitionException(CompetitionErrorCode.WRONG_FUND);
            }
            if (competitionDao.isClosed()) {
                throw new CompetitionException(CompetitionErrorCode.COMPETITION_CLOSED);
            }
            competitionDao.close();

            List<Application> applications = applicationDao.getAll();
            Collections.shuffle(applications);
            applications.sort((a1, a2) -> {
                int result = Double.compare(a2.getAverageRating(), a1.getAverageRating());
                if (result == 0) {
                    result = a1.getAmount() - a2.getAmount();
                }
                return result;
            });

            int fund = request.getFund();
            List<Application> winners = new ArrayList<>();
            for (Application application : applications) {
                if (application.getAverageRating() <= request.getThreshold()) {
                    break;
                }
                if (application.getAmount() <= fund) {
                    winners.add(application);
                    fund -= application.getAmount();
                }
            }
            return success(new SummarizeDtoResponse(toDtoList(winners), fund));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse clear() {
        competitionDao.clear();
        return success(new EmptyDtoResponse());
    }
}
