package ua.ozzy.apiback.model;

import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.enums.Role;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
public class BotApiInfo extends SystemUser {

    @Id
    private String id;

    @Enumerated(STRING)
    private ApiStatus status;

    private String accessKeyHash;

    @ManyToOne
    @JoinColumn(name = "active_group_id")
    private TelegramGroup activeGroup;

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

    public String getAccessKeyHash() {
        return accessKeyHash;
    }

    public void setAccessKeyHash(String accessKeyHash) {
        this.accessKeyHash = accessKeyHash;
    }

    public TelegramGroup getActiveGroup() {
        return activeGroup;
    }

    public void setActiveGroup(TelegramGroup activeGroup) {
        this.activeGroup = activeGroup;
    }

    public String getBotUrl() {
        return botUrl;
    }

    public void setBotUrl(String botUrl) {
        this.botUrl = botUrl;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return accessKeyHash;
    }

    @Override
    protected Role getRole() {
        return Role.BOT_API;
    }

}
