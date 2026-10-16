package academy.tochkavhoda.competition.dao;

public interface CompetitionDao {
    boolean isClosed();

    void close();

    void clear();
}
