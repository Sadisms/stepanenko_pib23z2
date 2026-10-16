package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

import java.util.List;

public interface ApplicationDao {
    Application insert(Application application) throws CompetitionException;

    Application getById(int id) throws CompetitionException;

    void delete(Application application) throws CompetitionException;

    List<Application> getByOwner(Participant participant);

    List<Application> getByDirections(List<String> directions);

    List<Application> getRatedBy(Expert expert);

    List<Application> getAll();

    void setRating(Application application, Expert expert, int rating) throws CompetitionException;

    void deleteRating(Application application, Expert expert) throws CompetitionException;
}
