package ua.ozzy.apiback.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot-api")
public class BotApiProperties {

    private String accessKey;

    private String feedbackUpdateUrl;

    private String statusReportUrl;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getFeedbackUpdateUrl() {
        return feedbackUpdateUrl;
    }

    public void setFeedbackUpdateUrl(String feedbackUpdateUrl) {
        this.feedbackUpdateUrl = feedbackUpdateUrl;
    }

    public String getStatusReportUrl() {
        return statusReportUrl;
    }

    public void setStatusReportUrl(String statusReportUrl) {
        this.statusReportUrl = statusReportUrl;
    }

}
