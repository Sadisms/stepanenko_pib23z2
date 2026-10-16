package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.service.ApplicationService;
import academy.tochkavhoda.competition.service.CompetitionService;
import academy.tochkavhoda.competition.service.UserService;

public class Server {
    private UserService userService = new UserService();
    private ApplicationService applicationService = new ApplicationService();
    private CompetitionService competitionService = new CompetitionService();

    public ServerResponse registerParticipant(String requestJsonString) {
        return userService.registerParticipant(requestJsonString);
    }

    public ServerResponse registerExpert(String requestJsonString) {
        return userService.registerExpert(requestJsonString);
    }

    public ServerResponse login(String requestJsonString) {
        return userService.login(requestJsonString);
    }

    public ServerResponse logout(String token) {
        return userService.logout(token);
    }

    public ServerResponse leaveServer(String token) {
        return userService.leaveServer(token);
    }

    public ServerResponse addApplication(String token, String requestJsonString) {
        return applicationService.addApplication(token, requestJsonString);
    }

    public ServerResponse cancelApplication(String token, String requestJsonString) {
        return applicationService.cancelApplication(token, requestJsonString);
    }

    public ServerResponse getMyApplications(String token) {
        return applicationService.getMyApplications(token);
    }

    public ServerResponse rateApplication(String token, String requestJsonString) {
        return applicationService.rateApplication(token, requestJsonString);
    }

    public ServerResponse deleteRating(String token, String requestJsonString) {
        return applicationService.deleteRating(token, requestJsonString);
    }

    public ServerResponse getApplicationsByDirections(String token, String requestJsonString) {
        return applicationService.getApplicationsByDirections(token, requestJsonString);
    }

    public ServerResponse getRatedApplications(String token) {
        return applicationService.getRatedApplications(token);
    }

    public ServerResponse summarize(String requestJsonString) {
        return competitionService.summarize(requestJsonString);
    }

    public ServerResponse clear() {
        return competitionService.clear();
    }
}
