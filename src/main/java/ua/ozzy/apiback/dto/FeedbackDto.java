package ua.ozzy.apiback.dto;

import java.time.LocalDateTime;

public class FeedbackDto {

    private String id;

    private Short rate;

    private String comment;

    private StatusDto status;

    private CustomerDto customer;

    private TelegramUserDto assignedUser;

    private LocalDateTime dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getRate() {
        return rate;
    }

    public void setRate(Short rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public TelegramUserDto getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(TelegramUserDto assignedUser) {
        this.assignedUser = assignedUser;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
