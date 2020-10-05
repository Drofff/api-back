package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Feedback;

import java.util.Optional;

public interface FeedbackService {

    Optional<Feedback> getLatestFeedback();

}
