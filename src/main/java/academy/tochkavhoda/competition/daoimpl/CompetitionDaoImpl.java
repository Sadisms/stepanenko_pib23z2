package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.CompetitionDao;
import academy.tochkavhoda.competition.database.Database;

public class CompetitionDaoImpl implements CompetitionDao {
    private Database database = Database.getInstance();

    @Override
    public boolean isClosed() {
        return database.isClosed();
    }

    @Override
    public void close() {
        database.close();
    }

    @Override
    public void clear() {
        database.clear();
    }
}
