package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.repository.TelegramUserRepository;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    public TelegramUserServiceImpl(TelegramUserRepository telegramUserRepository) {
        this.telegramUserRepository = telegramUserRepository;
    }

    @Override
    public TelegramUser getTelegramUserById(String id) {
        validateNotNull(id, "Telegram User id is required");
        return telegramUserRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Telegram User with id '" + id + "' is not registered"));
    }

    @Override
    public TelegramUser findOrCreateTelegramUser(TelegramUser telegramUser) {
        validateNotNull(telegramUser, "Telegram User is null");
        validateNotNull(telegramUser.getId(), "Telegram User id should be provided");
        return telegramUserRepository.findById(telegramUser.getId())
                .orElseGet(() -> createTelegramUser(telegramUser));
    }

    private TelegramUser createTelegramUser(TelegramUser telegramUser) {
        validate(telegramUser, "Telegram User should not be null");
        return telegramUserRepository.save(telegramUser);
    }

}
