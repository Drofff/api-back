package ua.ozzy.apiback.dto;

public class UpdateFeedbackInBotApiDto {

    String statusId;

    String assignedUserId;

    public UpdateFeedbackInBotApiDto(String statusId, String assignedUserId) {
        this.statusId = statusId;
        this.assignedUserId = assignedUserId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

}
