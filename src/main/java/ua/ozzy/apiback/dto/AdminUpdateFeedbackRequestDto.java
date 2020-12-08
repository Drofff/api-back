package ua.ozzy.apiback.dto;

public class AdminUpdateFeedbackRequestDto implements UpdateFeedbackRequest {

    private String statusId;

    private String assignedUserId;

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

    @Override
    public boolean hasAssignedUser() {
        return assignedUserId != null;
    }

}
