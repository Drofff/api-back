package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TelegramGroup {

    @Id
    private String id;

    @NotBlank(message = "Display name is required")
    private String name;

    @NotNull(message = "Telegram group chat id is required for bot to communicate")
    private Long chatId;

    @OneToMany(mappedBy = "activeGroup")
    private List<BotApiInfo> activeForBotApis = new ArrayList<>();

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

    public List<BotApiInfo> getActiveForBotApis() {
        return activeForBotApis;
    }

    public void setActiveForBotApis(List<BotApiInfo> activeForBotApis) {
        this.activeForBotApis = activeForBotApis;
    }

}
