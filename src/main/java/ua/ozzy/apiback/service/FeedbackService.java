package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.ozzy.apiback.model.Feedback;

import java.util.Optional;

public interface FeedbackService {

    Page<Feedback> getFeedbacks(Pageable pageable);

    Optional<Feedback> getLatestFeedback();

    Feedback createFeedback(Feedback feedback);

    /**
     * This method should be used to update a feedback by
     * Bot API's request. It assumes Bot API is completely
     * aware of the change. The method validates requester
     * permissions to update the feedback.
     *
     * @param feedback - entity to update
     * @param requesterId - telegram user id of the update requester
     * @return updated feedback
     */
    Feedback updateFeedbackForRequester(Feedback feedback, String requesterId);

    Feedback getFeedbackById(String id);

    /**
     * Updates the feedback without any permissions check
     * and notifies Bot API about the change. Intended to
     * be used for admin users only.
     *
     * @param feedback - entity to update
     */
    void updateFeedback(Feedback feedback);

}
