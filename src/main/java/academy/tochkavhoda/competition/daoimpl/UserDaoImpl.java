package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.UserDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.User;

import java.util.UUID;

public class UserDaoImpl implements UserDao {
    private Database database = Database.getInstance();

    @Override
    public void insert(User user) throws CompetitionException {
        if (!database.insertUser(user)) {
            throw new CompetitionException(CompetitionErrorCode.LOGIN_ALREADY_USED);
        }
    }

    @Override
    public User getByLoginAndPassword(String login, String password) throws CompetitionException {
        User user = database.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            throw new CompetitionException(CompetitionErrorCode.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User getByToken(String token) throws CompetitionException {
        User user = database.getUserByToken(token);
        if (user == null) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_TOKEN);
        }
        return user;
    }

    @Override
    public String login(User user) {
        String token = UUID.randomUUID().toString();
        database.insertToken(token, user);
        return token;
    }

    @Override
    public void logout(String token) throws CompetitionException {
        getByToken(token);
        database.deleteToken(token);
    }

    @Override
    public void delete(User user) {
        database.deleteUser(user);
    }
}
