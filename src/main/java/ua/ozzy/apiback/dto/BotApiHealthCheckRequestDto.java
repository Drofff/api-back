package ua.ozzy.apiback.dto;

public class BotApiHealthCheckRequestDto {

    private String botApiId;

    private Long periodMinutes;

    public String getBotApiId() {
        return botApiId;
    }

    public void setBotApiId(String botApiId) {
        this.botApiId = botApiId;
    }

    public Long getPeriodMinutes() {
        return periodMinutes;
    }

    public void setPeriodMinutes(Long periodMinutes) {
        this.periodMinutes = periodMinutes;
    }

}
