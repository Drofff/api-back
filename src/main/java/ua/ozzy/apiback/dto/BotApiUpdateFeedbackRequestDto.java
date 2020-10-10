package ua.ozzy.apiback.dto;

import ua.ozzy.apiback.model.Status;

public class BotApiUpdateFeedbackRequestDto {

    private Status status;

    private UpdateFeedbackRequestTelegramUserDto assignedUser;

    private String requesterId;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UpdateFeedbackRequestTelegramUserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UpdateFeedbackRequestTelegramUserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

}
