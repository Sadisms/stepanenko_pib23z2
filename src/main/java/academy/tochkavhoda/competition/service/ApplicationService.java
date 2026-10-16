package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.UserDao;
import academy.tochkavhoda.competition.daoimpl.ApplicationDaoImpl;
import academy.tochkavhoda.competition.daoimpl.UserDaoImpl;
import academy.tochkavhoda.competition.dto.request.AddApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.request.ApplicationIdDtoRequest;
import academy.tochkavhoda.competition.dto.request.DirectionsDtoRequest;
import academy.tochkavhoda.competition.dto.request.RateApplicationDtoRequest;
import academy.tochkavhoda.competition.dto.response.AddApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ApplicationsDtoResponse;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.model.User;
import academy.tochkavhoda.competition.server.ServerResponse;

import java.util.List;

public class ApplicationService extends BaseService {
    private UserDao userDao = new UserDaoImpl();
    private ApplicationDao applicationDao = new ApplicationDaoImpl();

    public ServerResponse addApplication(String token, String json) {
        try {
            Participant participant = getParticipant(token);
            AddApplicationDtoRequest request = parse(json, AddApplicationDtoRequest.class);
            validate(request);
            Application application = new Application(request.getName(), request.getDescription(),
                    request.getDirections(), request.getAmount(), participant);
            applicationDao.insert(application);
            return success(new AddApplicationDtoResponse(application.getId()));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse cancelApplication(String token, String json) {
        try {
            Participant participant = getParticipant(token);
            ApplicationIdDtoRequest request = parse(json, ApplicationIdDtoRequest.class);
            Application application = applicationDao.getById(request.getId());
            if (!application.getOwner().equals(participant)) {
                throw new CompetitionException(CompetitionErrorCode.NOT_OWNER);
            }
            applicationDao.delete(application);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse getMyApplications(String token) {
        try {
            Participant participant = getParticipant(token);
            List<Application> applications = applicationDao.getByOwner(participant);
            return success(new ApplicationsDtoResponse(toDtoList(applications)));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse rateApplication(String token, String json) {
        try {
            Expert expert = getExpert(token);
            RateApplicationDtoRequest request = parse(json, RateApplicationDtoRequest.class);
            if (request.getRating() < 1 || request.getRating() > 5) {
                throw new CompetitionException(CompetitionErrorCode.WRONG_RATING);
            }
            Application application = applicationDao.getById(request.getId());
            checkExpertDirection(expert, application);
            applicationDao.setRating(application, expert, request.getRating());
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse deleteRating(String token, String json) {
        try {
            Expert expert = getExpert(token);
            ApplicationIdDtoRequest request = parse(json, ApplicationIdDtoRequest.class);
            Application application = applicationDao.getById(request.getId());
            applicationDao.deleteRating(application, expert);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse getApplicationsByDirections(String token, String json) {
        try {
            Expert expert = getExpert(token);
            DirectionsDtoRequest request = parse(json, DirectionsDtoRequest.class);
            checkDirections(request.getDirections());
            for (String direction : request.getDirections()) {
                if (!expert.getDirections().contains(direction)) {
                    throw new CompetitionException(CompetitionErrorCode.NOT_EXPERT_DIRECTION);
                }
            }
            List<Application> applications = applicationDao.getByDirections(request.getDirections());
            return success(new ApplicationsDtoResponse(toDtoList(applications)));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse getRatedApplications(String token) {
        try {
            Expert expert = getExpert(token);
            List<Application> applications = applicationDao.getRatedBy(expert);
            return success(new ApplicationsDtoResponse(toDtoList(applications)));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    private Participant getParticipant(String token) throws CompetitionException {
        User user = userDao.getByToken(token);
        if (!(user instanceof Participant)) {
            throw new CompetitionException(CompetitionErrorCode.NOT_PARTICIPANT);
        }
        return (Participant) user;
    }

    private Expert getExpert(String token) throws CompetitionException {
        User user = userDao.getByToken(token);
        if (!(user instanceof Expert)) {
            throw new CompetitionException(CompetitionErrorCode.NOT_EXPERT);
        }
        return (Expert) user;
    }

    private static void checkExpertDirection(Expert expert, Application application) throws CompetitionException {
        for (String direction : application.getDirections()) {
            if (expert.getDirections().contains(direction)) {
                return;
            }
        }
        throw new CompetitionException(CompetitionErrorCode.NOT_EXPERT_DIRECTION);
    }

    private static void validate(AddApplicationDtoRequest request) throws CompetitionException {
        checkString(request.getName(), CompetitionErrorCode.WRONG_APPLICATION_NAME);
        checkString(request.getDescription(), CompetitionErrorCode.WRONG_APPLICATION_DESCRIPTION);
        checkDirections(request.getDirections());
        if (request.getAmount() <= 0) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_AMOUNT);
        }
    }
}
