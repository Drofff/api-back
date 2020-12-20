package ua.ozzy.apiback.model;

public class StatusReport {

    private String name;

    private long feedbacksCount;

    public StatusReport(String name, long feedbacksCount) {
        this.name = name;
        this.feedbacksCount = feedbacksCount;
    }

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
