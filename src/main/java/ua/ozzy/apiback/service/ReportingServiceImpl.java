package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final FeedbackService feedbackService;

    public ReportingServiceImpl(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public List<StatusReport> getStatusReports() {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();
        return generateStatusReports(feedbacks);
    }

    @Override
    public UserReport getReportForUser(TelegramUser user) {
        List<Feedback> feedbacksAssignedToUser = feedbackService.getFeedbacksAssignedTo(user);
        List<StatusReport> statusReports = generateStatusReports(feedbacksAssignedToUser);
        return new UserReport(user.getUsername(), statusReports);
    }

    private List<StatusReport> generateStatusReports(List<Feedback> feedbacks) {
        Map<Status, Long> statusesOccurrences = countStatusOccurrences(feedbacks);
        return asStatusReports(statusesOccurrences);
    }

    private Map<Status, Long> countStatusOccurrences(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .collect(groupingBy(Feedback::getStatus, counting()));
    }

    private List<StatusReport> asStatusReports(Map<Status, Long> statusesOccurrences) {
        return statusesOccurrences.entrySet().stream()
                .map(this::asStatusReport)
                .collect(toList());
    }

    private StatusReport asStatusReport(Map.Entry<Status, Long> statusOccurrences) {
        String statusName = statusOccurrences.getKey().getName();
        Long statusOccurrencesCount = statusOccurrences.getValue();
        return new StatusReport(statusName, statusOccurrencesCount);
    }

}
