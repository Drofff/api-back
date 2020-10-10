package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.repository.FeedbackRepository;

import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Page<Feedback> getFeedbacks(Pageable pageable) {
        validateNotNull(pageable, "Missing paging info");
        return feedbackRepository.findByOrderByDateTimeDesc(pageable);
    }

    @Override
    public Optional<Feedback> getLatestFeedback() {
        return feedbackRepository.findFirstByOrderByDateTimeDesc();
    }

}
