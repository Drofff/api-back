package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramMessage;
import ua.ozzy.apiback.repository.TelegramMessageRepository;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {

    private final TelegramMessageRepository telegramMessageRepository;

    public TelegramMessageServiceImpl(TelegramMessageRepository telegramMessageRepository) {
        this.telegramMessageRepository = telegramMessageRepository;
    }

    @Override
    public void createTelegramMessage(TelegramMessage telegramMessage) {
        validate(telegramMessage, "No message data has been provided");
        validateHasUniqueId(telegramMessage);
        validateFeedbackHasNoMessage(telegramMessage.getFeedback());
        telegramMessageRepository.save(telegramMessage);
    }

    private void validateHasUniqueId(TelegramMessage telegramMessage) {
        if (existsMessageWithId(telegramMessage.getId())) {
            throw new ValidationException("Message with such an id already exists");
        }
    }

    private boolean existsMessageWithId(String id) {
        return telegramMessageRepository.findById(id).isPresent();
    }

    private void validateFeedbackHasNoMessage(Feedback feedback) {
        if (feedbackHasMessage(feedback)) {
            throw new ValidationException("Feedback does already have a message associated with it");
        }
    }

    private boolean feedbackHasMessage(Feedback feedback) {
        return telegramMessageRepository.findByFeedback(feedback).isPresent();
    }

    @Override
    public TelegramMessage getTelegramMessageOfFeedback(Feedback feedback) {
        validateNotNull(feedback, "Feedback is required");
        validateNotNull(feedback.getId(), "Feedback must obtain an id");
        return telegramMessageRepository.findByFeedback(feedback)
                .orElseThrow(() -> new ValidationException("No message has been associated with the feedback"));
    }

}
