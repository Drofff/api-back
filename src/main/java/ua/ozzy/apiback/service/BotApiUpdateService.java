package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.StatusReport;

import java.util.List;

public interface BotApiUpdateService {

    void sendFeedbackUpdate(Feedback feedback);

    void sendStatusReports(List<StatusReport> statusReports);

}
