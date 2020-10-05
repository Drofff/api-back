package ua.ozzy.apiback.dto;

import java.time.LocalDateTime;

public class BotApiHealthCheckDto {

    private String id;

    private LocalDateTime dateTime;

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

}
