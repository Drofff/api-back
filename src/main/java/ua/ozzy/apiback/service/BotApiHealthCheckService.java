package ua.ozzy.apiback.service;

import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.model.BotApiHealthCheck;

import java.util.List;

public interface BotApiHealthCheckService {

    ApiStatus getBotApiStatus(String botApiId);

    List<BotApiHealthCheck> getHealthChecksOfBotApiForPeriod(String botApiId, Long periodMinutes);

    void submitHealthCheck(String botApiId);

}
