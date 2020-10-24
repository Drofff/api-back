package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.model.BotApiHealthCheck;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.repository.BotApiHealthCheckRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class BotApiHealthCheckServiceImpl implements BotApiHealthCheckService {

    private final BotApiHealthCheckRepository botApiHealthCheckRepository;

    private final BotApiInfoService botApiInfoService;

    public BotApiHealthCheckServiceImpl(BotApiHealthCheckRepository botApiHealthCheckRepository,
                                        BotApiInfoService botApiInfoService) {
        this.botApiHealthCheckRepository = botApiHealthCheckRepository;
        this.botApiInfoService = botApiInfoService;
    }

    /**
     * We expect Bot API to send us a health check each 3 minutes.
     * If we don't receive a health check signal in 5 minutes we consider Bot API to be unhealthy (down)
     *
     * @param botApiId id of Bot API being checked
     * @return a status of the Bot API
     */
    @Override
    public ApiStatus getBotApiStatus(String botApiId) {
        validateNotNull(botApiId, "Can not get a status of a bot api with null id");
        List<BotApiHealthCheck> healthChecks = getHealthChecksOfBotApiForPeriod(botApiId, 5L);
        return healthChecks.isEmpty() ? ApiStatus.DOWN : ApiStatus.UP;
    }

    @Override
    public List<BotApiHealthCheck> getHealthChecksOfBotApiForPeriod(String botApiId, Long periodMinutes) {
        validateNotNull(botApiId, "Bot API id is required for the health checks to be found");
        validateNotNull(periodMinutes, "Health checks monitoring requires a period in minutes");
        BotApiInfo botApiInfo = botApiInfoService.getBotApiInfoById(botApiId);
        LocalDateTime startTime = getHealthChecksStartTime(periodMinutes);
        return botApiHealthCheckRepository.findByBotApiInfoAndDateTimeGreaterThanEqual(botApiInfo, startTime);
    }

    private LocalDateTime getHealthChecksStartTime(Long periodMinutes) {
        LocalDateTime now = LocalDateTime.now();
        return now.minusMinutes(periodMinutes);
    }

    @Override
    public void submitHealthCheck(String botApiId) {
        validateNotNull(botApiId, "Please provide an id of the source bot api");
        BotApiInfo botApiInfo = botApiInfoService.getBotApiInfoById(botApiId);
        BotApiHealthCheck healthCheck = BotApiHealthCheck.forBotApi(botApiInfo);
        botApiHealthCheckRepository.save(healthCheck);
    }

}
