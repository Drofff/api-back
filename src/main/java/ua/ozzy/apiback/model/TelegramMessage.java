package ua.ozzy.apiback.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class TelegramMessage {

    @Id
    @NotBlank(message = "Message id is required")
    private String id;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    @NotNull(message = "Feedback should be provided")
    private Feedback feedback;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

}
