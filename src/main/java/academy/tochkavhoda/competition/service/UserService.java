package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.UserDao;
import academy.tochkavhoda.competition.daoimpl.UserDaoImpl;
import academy.tochkavhoda.competition.dto.request.LoginDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertDtoRequest;
import academy.tochkavhoda.competition.dto.request.RegisterParticipantDtoRequest;
import academy.tochkavhoda.competition.dto.response.EmptyDtoResponse;
import academy.tochkavhoda.competition.dto.response.LoginDtoResponse;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.model.User;
import academy.tochkavhoda.competition.server.ServerResponse;

public class UserService extends BaseService {
    private UserDao userDao = new UserDaoImpl();

    public ServerResponse registerParticipant(String json) {
        try {
            RegisterParticipantDtoRequest request = parse(json, RegisterParticipantDtoRequest.class);
            validate(request);
            Participant participant = new Participant(request.getFirstName(), request.getLastName(),
                    request.getFirmName(), request.getLogin(), request.getPassword());
            userDao.insert(participant);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse registerExpert(String json) {
        try {
            RegisterExpertDtoRequest request = parse(json, RegisterExpertDtoRequest.class);
            validate(request);
            Expert expert = new Expert(request.getFirstName(), request.getLastName(),
                    request.getDirections(), request.getLogin(), request.getPassword());
            userDao.insert(expert);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse login(String json) {
        try {
            LoginDtoRequest request = parse(json, LoginDtoRequest.class);
            User user = userDao.getByLoginAndPassword(request.getLogin(), request.getPassword());
            String token = userDao.login(user);
            return success(new LoginDtoResponse(token));
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse logout(String token) {
        try {
            userDao.logout(token);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    public ServerResponse leaveServer(String token) {
        try {
            User user = userDao.getByToken(token);
            userDao.delete(user);
            return success(new EmptyDtoResponse());
        } catch (CompetitionException ex) {
            return error(ex);
        }
    }

    private static void validate(RegisterParticipantDtoRequest request) throws CompetitionException {
        checkString(request.getFirstName(), CompetitionErrorCode.WRONG_FIRST_NAME);
        checkString(request.getLastName(), CompetitionErrorCode.WRONG_LAST_NAME);
        checkString(request.getFirmName(), CompetitionErrorCode.WRONG_FIRM_NAME);
        checkLogin(request.getLogin());
        checkPassword(request.getPassword());
    }

    private static void validate(RegisterExpertDtoRequest request) throws CompetitionException {
        checkString(request.getFirstName(), CompetitionErrorCode.WRONG_FIRST_NAME);
        checkString(request.getLastName(), CompetitionErrorCode.WRONG_LAST_NAME);
        checkDirections(request.getDirections());
        checkLogin(request.getLogin());
        checkPassword(request.getPassword());
    }

    private static void checkLogin(String login) throws CompetitionException {
        if (login == null || login.length() < 3) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_LOGIN);
        }
    }

    private static void checkPassword(String password) throws CompetitionException {
        if (password == null || password.length() < 6) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_PASSWORD);
        }
    }
}
