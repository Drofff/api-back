package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.StatusReport;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.model.UserReport;

import java.util.List;

public interface ReportingService {

    List<StatusReport> getStatusReports();

    UserReport getReportForUser(TelegramUser user);

}
