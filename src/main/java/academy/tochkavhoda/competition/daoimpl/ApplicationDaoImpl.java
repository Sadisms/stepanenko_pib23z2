package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private Database database = Database.getInstance();

    @Override
    public Application insert(Application application) throws CompetitionException {
        if (database.isClosed()) {
            throw new CompetitionException(CompetitionErrorCode.COMPETITION_CLOSED);
        }
        return database.insertApplication(application);
    }

    @Override
    public Application getById(int id) throws CompetitionException {
        Application application = database.getApplicationById(id);
        if (application == null) {
            throw new CompetitionException(CompetitionErrorCode.APPLICATION_NOT_FOUND);
        }
        return application;
    }

    @Override
    public void delete(Application application) throws CompetitionException {
        if (database.isClosed()) {
            throw new CompetitionException(CompetitionErrorCode.COMPETITION_CLOSED);
        }
        database.deleteApplication(application);
    }

    @Override
    public List<Application> getByOwner(Participant participant) {
        return database.getApplicationsByOwner(participant);
    }

    @Override
    public List<Application> getByDirections(List<String> directions) {
        return database.getApplicationsByDirections(directions);
    }

    @Override
    public List<Application> getRatedBy(Expert expert) {
        return database.getApplicationsRatedBy(expert);
    }

    @Override
    public List<Application> getAll() {
        return database.getAllApplications();
    }

    @Override
    public void setRating(Application application, Expert expert, int rating) throws CompetitionException {
        if (database.isClosed()) {
            throw new CompetitionException(CompetitionErrorCode.COMPETITION_CLOSED);
        }
        database.setRating(application, expert, rating);
    }

    @Override
    public void deleteRating(Application application, Expert expert) throws CompetitionException {
        if (database.isClosed()) {
            throw new CompetitionException(CompetitionErrorCode.COMPETITION_CLOSED);
        }
        if (!database.deleteRating(application, expert)) {
            throw new CompetitionException(CompetitionErrorCode.RATING_NOT_FOUND);
        }
    }
}
