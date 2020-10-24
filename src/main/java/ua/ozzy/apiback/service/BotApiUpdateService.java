package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Feedback;

public interface BotApiUpdateService {

    void sendFeedbackUpdate(Feedback feedback);

}
