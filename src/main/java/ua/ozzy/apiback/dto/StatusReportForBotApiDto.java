package ua.ozzy.apiback.dto;

public class StatusReportForBotApiDto {

    private String name;

    private long feedbacksCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFeedbacksCount() {
        return feedbacksCount;
    }

    public void setFeedbacksCount(long feedbacksCount) {
        this.feedbacksCount = feedbacksCount;
    }

}
