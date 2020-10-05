package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BotApiHealthCheck {

    @Id
    private String id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "bot_api_info_id")
    private BotApiInfo botApiInfo;

    public static BotApiHealthCheck forBotApi(BotApiInfo botApiInfo) {
        String generatedId = UUID.randomUUID().toString();
        LocalDateTime timeOfCheck = LocalDateTime.now();
        return new BotApiHealthCheck(generatedId, timeOfCheck, botApiInfo);
    }

    public BotApiHealthCheck() {}

    private BotApiHealthCheck(String id, LocalDateTime dateTime, BotApiInfo botApiInfo) {
        this.id = id;
        this.dateTime = dateTime;
        this.botApiInfo = botApiInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BotApiInfo getBotApiInfo() {
        return botApiInfo;
    }

    public void setBotApiInfo(BotApiInfo botApiInfo) {
        this.botApiInfo = botApiInfo;
    }

}
