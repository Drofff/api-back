package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TelegramGroup {

    @Id
    private String id;

    private String name;

    private Long chatId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

}
