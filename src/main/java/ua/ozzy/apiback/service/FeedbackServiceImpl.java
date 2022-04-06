package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.repository.FeedbackRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final BotApiUpdateService botApiUpdateService;

    private final FeedbackRepository feedbackRepository;
    private final EntityManager entityManager;

    public FeedbackServiceImpl(BotApiUpdateService botApiUpdateService, FeedbackRepository feedbackRepository,
                               EntityManager entityManager) {
        this.botApiUpdateService = botApiUpdateService;
        this.feedbackRepository = feedbackRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Page<Feedback> getFeedbacks(Pageable pageable) {
        validateNotNull(pageable, "Missing paging info");
        return feedbackRepository.findByOrderByDateTimeDesc(pageable);
    }

    @Override
    public List<Feedback> getFeedbacksAssignedTo(TelegramUser telegramUser) {
        validateNotNull(telegramUser, "Telegram user must not be null");
        validateNotNull(telegramUser.getId(), "Telegram user must obtain an id");
        return feedbackRepository.findByAssignedUser(telegramUser);
    }

    @Override
    public Optional<Feedback> getLatestFeedback() {
        return feedbackRepository.findFirstByOrderByDateTimeDesc();
    }

    @Override
    public Feedback getFeedbackById(String id) {
        validateNotNull(id, "Feedback id should not be null");
        return feedbackRepository.findById(id).map(f -> {
            entityManager.detach(f);
            return f;
        }).orElseThrow(() -> new ValidationException("Feedback with such an id doesn't exist"));
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        validate(feedback, "Can not update a null feedback");
        validateNotNull(feedback.getId(), "Missing feedback id");
        feedbackRepository.save(feedback);
        botApiUpdateService.sendFeedbackUpdate(feedback);
    }

}
