package ua.ozzy.apiback.model;

import org.hibernate.validator.constraints.URL;
import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
public class BotApiInfo extends SystemUser {

    @Id
    private String id;

    @Enumerated(STRING)
    private ApiStatus status;

    @OneToMany
    @JoinColumn(name = "bot_api_id")
    private List<AccessKey> accessKeys = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "active_group_id")
    private TelegramGroup activeGroup;

    @NotBlank(message = "Bot url is required")
    @URL(message = "Invalid URL format")
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

    public List<AccessKey> getAccessKeys() {
        return accessKeys;
    }

    public void setAccessKeys(List<AccessKey> accessKeys) {
        this.accessKeys = accessKeys;
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
        if (accessKeys.isEmpty()) {
            return "<unknown>";
        }
        return accessKeys.get(0).getKeyHash();
    }

    @Override
    protected Role getRole() {
        return Role.BOT_API;
    }

}
