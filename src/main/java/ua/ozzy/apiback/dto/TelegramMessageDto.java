package ua.ozzy.apiback.dto;

public class TelegramMessageDto {

    private final String messageId;

    public TelegramMessageDto(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

}
