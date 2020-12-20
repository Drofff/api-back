package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramMessage;

public interface TelegramMessageService {

    void createTelegramMessage(TelegramMessage telegramMessage);

    TelegramMessage getTelegramMessageOfFeedback(Feedback feedback);

}
