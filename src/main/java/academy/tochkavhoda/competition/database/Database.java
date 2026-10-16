package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Database instance;

    private Map<String, User> users = new HashMap<>();
    private Map<String, User> tokens = new HashMap<>();
    private Map<Integer, Application> applications = new HashMap<>();
    private int nextApplicationId = 1;
    private boolean closed = false;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void clear() {
        users.clear();
        tokens.clear();
        applications.clear();
        nextApplicationId = 1;
        closed = false;
    }

    public boolean insertUser(User user) {
        if (users.containsKey(user.getLogin())) {
            return false;
        }
        users.put(user.getLogin(), user);
        return true;
    }

    public User getUserByLogin(String login) {
        return users.get(login);
    }

    public User getUserByToken(String token) {
        return tokens.get(token);
    }

    public void insertToken(String token, User user) {
        tokens.put(token, user);
    }

    public void deleteToken(String token) {
        tokens.remove(token);
    }

    public void deleteUser(User user) {
        users.remove(user.getLogin());
        tokens.values().removeIf(u -> u.equals(user));
        if (user instanceof Participant) {
            applications.values().removeIf(a -> a.getOwner().equals(user));
        }
        if (user instanceof Expert) {
            for (Application application : applications.values()) {
                application.getRatings().remove(user.getLogin());
            }
        }
    }

    public Application insertApplication(Application application) {
        application.setId(nextApplicationId++);
        applications.put(application.getId(), application);
        return application;
    }

    public Application getApplicationById(int id) {
        return applications.get(id);
    }

    public void deleteApplication(Application application) {
        applications.remove(application.getId());
    }

    public List<Application> getApplicationsByOwner(Participant participant) {
        List<Application> result = new ArrayList<>();
        for (Application application : applications.values()) {
            if (application.getOwner().equals(participant)) {
                result.add(application);
            }
        }
        return result;
    }

    public List<Application> getApplicationsByDirections(List<String> directions) {
        List<Application> result = new ArrayList<>();
        for (Application application : applications.values()) {
            for (String direction : directions) {
                if (application.getDirections().contains(direction)) {
                    result.add(application);
                    break;
                }
            }
        }
        return result;
    }

    public List<Application> getApplicationsRatedBy(Expert expert) {
        List<Application> result = new ArrayList<>();
        for (Application application : applications.values()) {
            if (application.getRatings().containsKey(expert.getLogin())) {
                result.add(application);
            }
        }
        return result;
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications.values());
    }

    public void setRating(Application application, Expert expert, int rating) {
        application.getRatings().put(expert.getLogin(), rating);
    }

    public boolean deleteRating(Application application, Expert expert) {
        return application.getRatings().remove(expert.getLogin()) != null;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
    }
}
