package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TelegramUser {

    @Id
    private String id;

    private String username;

    private Long userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
