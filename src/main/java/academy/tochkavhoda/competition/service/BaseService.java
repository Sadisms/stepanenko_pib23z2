package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dto.response.ApplicationDtoResponse;
import academy.tochkavhoda.competition.dto.response.ErrorDtoResponse;
import academy.tochkavhoda.competition.exception.CompetitionErrorCode;
import academy.tochkavhoda.competition.exception.CompetitionException;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.server.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

public class BaseService {
    protected Gson gson = new Gson();

    protected <T> T parse(String json, Class<T> dtoClass) throws CompetitionException {
        T result;
        try {
            result = gson.fromJson(json, dtoClass);
        } catch (JsonSyntaxException ex) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_JSON);
        }
        return result;
    }

    protected ServerResponse success(Object dto) {
        return new ServerResponse(200, gson.toJson(dto));
    }

    protected ServerResponse error(CompetitionException ex) {
        return new ServerResponse(400, gson.toJson(new ErrorDtoResponse(ex.getMessage())));
    }

    protected static void checkString(String value, CompetitionErrorCode errorCode) throws CompetitionException {
        if (value == null || value.isEmpty()) {
            throw new CompetitionException(errorCode);
        }
    }

    protected static void checkDirections(List<String> directions) throws CompetitionException {
        if (directions == null || directions.isEmpty()) {
            throw new CompetitionException(CompetitionErrorCode.WRONG_DIRECTIONS);
        }
        for (String direction : directions) {
            checkString(direction, CompetitionErrorCode.WRONG_DIRECTIONS);
        }
    }

    protected ApplicationDtoResponse toDto(Application application) {
        return new ApplicationDtoResponse(
                application.getId(),
                application.getName(),
                application.getDescription(),
                application.getDirections(),
                application.getAmount(),
                application.getOwner().getFirmName(),
                application.getAverageRating(),
                application.getRatings().size());
    }

    protected List<ApplicationDtoResponse> toDtoList(List<Application> applications) {
        List<ApplicationDtoResponse> result = new ArrayList<>();
        for (Application application : applications) {
            result.add(toDto(application));
        }
        return result;
    }
}
