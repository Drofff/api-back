package ua.ozzy.apiback.dto;

public class CreateTelegramGroupDto {

    private String name;

    private Long chatId;

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
