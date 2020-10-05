package ua.ozzy.apiback.dto;

import ua.ozzy.apiback.enums.ApiStatus;

import java.time.LocalDateTime;

public class BotApiInfoDto {

    private String id;

    private ApiStatus status;

    private LocalDateTime lastFeedbackDateTime;

    private String activeGroup;

    private String botUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastFeedbackDateTime() {
        return lastFeedbackDateTime;
    }

    public void setLastFeedbackDateTime(LocalDateTime lastFeedbackDateTime) {
        this.lastFeedbackDateTime = lastFeedbackDateTime;
    }

    public String getActiveGroup() {
        return activeGroup;
    }

    public void setActiveGroup(String activeGroup) {
        this.activeGroup = activeGroup;
    }

    public String getBotUrl() {
        return botUrl;
    }

    public void setBotUrl(String botUrl) {
        this.botUrl = botUrl;
    }

}
