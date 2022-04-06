package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Feedback;

public interface BotApiFeedbackService {

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

}
