package ua.ozzy.apiback.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.FeedbackRepository;
import ua.ozzy.apiback.util.DbUtil;

import java.time.LocalDateTime;
import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final CustomerService customerService;
    private final StatusService statusService;

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(CustomerService customerService, StatusService statusService,
                               FeedbackRepository feedbackRepository) {
        this.customerService = customerService;
        this.statusService = statusService;
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

    @Override
    public void createFeedback(Feedback feedback) {
        validate(feedback, "Feedback should not be null");
        Customer customer = customerService.findOrCreateCustomer(feedback.getCustomer());
        feedback.setCustomer(customer);
        feedback.setId(DbUtil.generateId());
        Status defaultStatus = statusService.getDefaultStatus();
        feedback.setStatus(defaultStatus);
        feedback.setDateTime(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    @Override
    public void updateFeedbackForRequester(Feedback feedback, String requesterId) {
        validateNotNull(feedback, "Feedback is null");
        Feedback originalFeedback = getFeedbackById(feedback.getId());
        if (!originalFeedback.canBeModifiedBy(requesterId)) {
            throw new ValidationException("Permission denied for user with id " + requesterId);
        }
        updateFeedback(feedback);
    }

    @Override
    public Feedback getFeedbackById(String id) {
        validateNotNull(id, "Feedback id should not be null");
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Feedback with such an id doesn't exist"));
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        validate(feedback, "Can not update a null feedback");
        validateNotNull(feedback.getId(), "Missing feedback id");
        feedbackRepository.save(feedback);
    }

}
