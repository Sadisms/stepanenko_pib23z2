package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.User;

public interface UserDao {
    void insert(User user) throws CompetitionException;

    User getByLoginAndPassword(String login, String password) throws CompetitionException;

    User getByToken(String token) throws CompetitionException;

    String login(User user);

    void logout(String token) throws CompetitionException;

    void delete(User user);
}
