package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.dto.UserReportDto;
import ua.ozzy.apiback.job.ReportingJob;
import ua.ozzy.apiback.mapper.UserReportDtoMapper;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.model.UserReport;
import ua.ozzy.apiback.service.ReportingService;
import ua.ozzy.apiback.service.TelegramUserService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/reports")
public class ReportingController {

    private final ReportingService reportingService;
    private final TelegramUserService telegramUserService;
    private final ReportingJob reportingJob;

    private final UserReportDtoMapper userReportDtoMapper;

    public ReportingController(ReportingService reportingService, TelegramUserService telegramUserService,
                               ReportingJob reportingJob, UserReportDtoMapper userReportDtoMapper) {
        this.reportingService = reportingService;
        this.telegramUserService = telegramUserService;
        this.reportingJob = reportingJob;
        this.userReportDtoMapper = userReportDtoMapper;
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserReportDto> getReportForUser(@PathVariable String userId) {
        TelegramUser user = telegramUserService.getTelegramUserById(userId);
        UserReport userReport = reportingService.getReportForUser(user);
        UserReportDto reportDto = userReportDtoMapper.toDto(userReport);
        return ok(reportDto);
    }

    @GetMapping("/statuses")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> triggerStatusesReport() {
        reportingJob.sendStatusReports();
        return ok(new MessageDto("Started sending status reports"));
    }

}
