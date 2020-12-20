package ua.ozzy.apiback.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.model.StatusReport;
import ua.ozzy.apiback.service.BotApiUpdateService;
import ua.ozzy.apiback.service.ReportingService;

import java.util.List;

@Component
public class ReportingJob {

    private final ReportingService reportingService;
    private final BotApiUpdateService botApiUpdateService;

    public ReportingJob(ReportingService reportingService, BotApiUpdateService botApiUpdateService) {
        this.reportingService = reportingService;
        this.botApiUpdateService = botApiUpdateService;
    }

    @Scheduled(cron = "0 0 11 * * ?") // 11 AM every Monday
    public void sendStatusReports() {
        List<StatusReport> statusReports = reportingService.getStatusReports();
        botApiUpdateService.sendStatusReports(statusReports);
    }

}
