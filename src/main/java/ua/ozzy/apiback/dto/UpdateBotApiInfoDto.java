package ua.ozzy.apiback.dto;

public class UpdateBotApiInfoDto {

    private String botUrl;

    private String telegramGroupId;

    public String getBotUrl() {
        return botUrl;
    }

    public void setBotUrl(String botUrl) {
        this.botUrl = botUrl;
    }

    public String getTelegramGroupId() {
        return telegramGroupId;
    }

    public void setTelegramGroupId(String telegramGroupId) {
        this.telegramGroupId = telegramGroupId;
    }

}
