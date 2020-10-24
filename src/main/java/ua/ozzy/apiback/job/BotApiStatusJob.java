package ua.ozzy.apiback.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.service.BotApiHealthCheckService;
import ua.ozzy.apiback.service.BotApiInfoService;

@Component
public class BotApiStatusJob {

    private static final Logger LOG = LoggerFactory.getLogger(BotApiStatusJob.class);

    private static final long STATUS_UPDATE_DELAY_MILLIS = 300_000L; // 5 minutes

    private final BotApiInfoService botApiInfoService;
    private final BotApiHealthCheckService botApiHealthCheckService;

    public BotApiStatusJob(BotApiInfoService botApiInfoService, BotApiHealthCheckService botApiHealthCheckService) {
        this.botApiInfoService = botApiInfoService;
        this.botApiHealthCheckService = botApiHealthCheckService;
    }

    @Scheduled(initialDelay = JobConstants.INITIAL_DELAY_MILLIS, fixedDelay = STATUS_UPDATE_DELAY_MILLIS)
    public void updateBotApiStatus() {
        LOG.info("Running Bot API status update job");
        BotApiInfo botApiInfo = botApiInfoService.getFirstBotApiInfo();
        ApiStatus apiStatus = botApiHealthCheckService.getBotApiStatus(botApiInfo.getId());
        LOG.info("Bot API status has been resolved as '{}'", apiStatus);
        botApiInfo.setStatus(apiStatus);
        botApiInfoService.updateBotApiInfo(botApiInfo);
    }

}
