package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    List<Feedback> getFeedbacks();

    Page<Feedback> getFeedbacks(Pageable pageable);

    List<Feedback> getFeedbacksAssignedTo(TelegramUser telegramUser);

    Optional<Feedback> getLatestFeedback();

    Feedback getFeedbackById(String id);

    void updateFeedback(Feedback feedback);

}
