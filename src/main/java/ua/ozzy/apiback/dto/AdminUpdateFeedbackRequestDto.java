package ua.ozzy.apiback.dto;

import ua.ozzy.apiback.model.Status;

public class AdminUpdateFeedbackRequestDto {

    private Status status;

    private String assignedUserId;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

}
