package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
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
    public void submitHealthCheck(String botApiId, String accessKey) {
        validateNotNull(botApiId, "Please provide an id of a source bot api");
        validateNotNull(accessKey, "Missing an access key");
        BotApiInfo botApiInfo = botApiInfoService.getBotApiInfoById(botApiId);
        if (!botApiInfoService.isValidAccessKeyForBotApi(accessKey, botApiInfo)) {
            throw new ValidationException("Invalid access key");
        }
        BotApiHealthCheck healthCheck = BotApiHealthCheck.forBotApi(botApiInfo);
        botApiHealthCheckRepository.save(healthCheck);
    }

}
