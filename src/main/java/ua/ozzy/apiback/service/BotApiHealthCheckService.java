package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.BotApiHealthCheck;

import java.util.List;

public interface BotApiHealthCheckService {

    List<BotApiHealthCheck> getHealthChecksOfBotApiForPeriod(String botApiId, Long periodMinutes);

    void submitHealthCheck(String botApiId, String accessKey);

}
