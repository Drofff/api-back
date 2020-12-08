package ua.ozzy.apiback.dto;

public class BotApiUpdateFeedbackRequestDto implements UpdateFeedbackRequest {

    private String statusId;

    private UpdateFeedbackRequestTelegramUserDto assignedUser;

    private String requesterId;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public UpdateFeedbackRequestTelegramUserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UpdateFeedbackRequestTelegramUserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public boolean hasAssignedUser() {
        return assignedUser != null;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

}
