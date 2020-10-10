package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.TelegramUser;

public interface TelegramUserService {

    TelegramUser getTelegramUserById(String id);

    TelegramUser findOrCreateTelegramUser(TelegramUser telegramUser);

}
