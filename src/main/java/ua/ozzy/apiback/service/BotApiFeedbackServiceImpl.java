package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.FeedbackRepository;
import ua.ozzy.apiback.util.DbUtil;

import java.time.LocalDateTime;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class BotApiFeedbackServiceImpl implements BotApiFeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final CustomerService customerService;
    private final StatusService statusService;
    private final CrmService crmService;

    public BotApiFeedbackServiceImpl(FeedbackRepository feedbackRepository, CustomerService customerService,
                                     StatusService statusService, CrmService crmService) {
        this.feedbackRepository = feedbackRepository;
        this.customerService = customerService;
        this.statusService = statusService;
        this.crmService = crmService;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        validate(feedback, "Feedback should not be null");
        initFeedbackDefaults(feedback);
        Customer customer = customerService.findOrCreateCustomer(feedback.getCustomer());
        feedback.setCustomer(customer);
        feedbackRepository.save(feedback);
        crmService.createDeal(feedback);
        return feedback;
    }

    private void initFeedbackDefaults(Feedback feedback) {
        feedback.setId(DbUtil.generateId());
        Status defaultStatus = statusService.getDefaultStatus();
        feedback.setStatus(defaultStatus);
        feedback.setDateTime(LocalDateTime.now());
    }

    @Override
    public Feedback updateFeedbackForRequester(Feedback feedback, String requesterId) {
        validate(feedback, "Feedback is null");
        Feedback originalFeedback = getFeedbackById(feedback.getId());
        if (!originalFeedback.canBeModifiedBy(requesterId)) {
            throw new ValidationException("Permission denied for user with id " + requesterId);
        }
        return feedbackRepository.save(feedback);
    }

    private Feedback getFeedbackById(String id) {
        validateNotNull(id, "Missing feedback id");
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Feedback with id " + id + " doesn't exist"));
    }

}
