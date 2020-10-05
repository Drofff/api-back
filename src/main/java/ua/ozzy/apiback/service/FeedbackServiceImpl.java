package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.repository.FeedbackRepository;

import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Optional<Feedback> getLatestFeedback() {
        return feedbackRepository.findFirstByOrderByDateTimeDesc();
    }

}
