package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.ozzy.apiback.model.Feedback;

import java.util.Optional;

public interface FeedbackService {

    Page<Feedback> getFeedbacks(Pageable pageable);

    Optional<Feedback> getLatestFeedback();

    void createFeedback(Feedback feedback);

    Feedback updateFeedbackForRequester(Feedback feedback, String requesterId);

    Feedback getFeedbackById(String id);

    Feedback updateFeedback(Feedback feedback);

}
