package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Feedback {

    @Id
    private String id;

    @NotNull(message = "Rate is required")
    @Min(1) @Max(5)
    private Short rate;

    @NotBlank(message = "Comment should be provided")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "Feedback should contain customer's info")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "telegram_user_id")
    private TelegramUser assignedUser;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TelegramUser getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(TelegramUser assignedUser) {
        this.assignedUser = assignedUser;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean canBeModifiedBy(String telegramUserId) {
        return assignedUser == null || assignedUser.getId().equals(telegramUserId);
    }

}
