package ua.ozzy.apiback.dto;

public class BotApiSubmitHealthCheckDto {

    private String botApiId;

    private String accessKey;

    public String getBotApiId() {
        return botApiId;
    }

    public void setBotApiId(String botApiId) {
        this.botApiId = botApiId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

}
