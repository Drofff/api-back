package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.TelegramGroup;
import ua.ozzy.apiback.repository.TelegramGroupRepository;
import ua.ozzy.apiback.util.DbUtil;

import java.util.List;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class TelegramGroupServiceImpl implements TelegramGroupService {

    private final TelegramGroupRepository telegramGroupRepository;

    public TelegramGroupServiceImpl(TelegramGroupRepository telegramGroupRepository) {
        this.telegramGroupRepository = telegramGroupRepository;
    }

    @Override
    public TelegramGroup getTelegramGroupById(String id) {
        validateNotNull(id, "Missing telegram group id");
        return telegramGroupRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Unknown telegram group with id '" + id + "'"));
    }

    @Override
    public List<TelegramGroup> getTelegramGroups() {
        return telegramGroupRepository.findAll();
    }

    @Override
    public void createTelegramGroup(TelegramGroup telegramGroup) {
        validate(telegramGroup, "Telegram group is null");
        validateIsUniqueChatId(telegramGroup.getChatId());
        telegramGroup.setId(DbUtil.generateId());
        telegramGroupRepository.save(telegramGroup);
    }

    private void validateIsUniqueChatId(Long chatId) {
        if (existsGroupWithChatId(chatId)) {
            throw new ValidationException("Telegram group with chat id '" + chatId + "' already exists");
        }
    }

    private boolean existsGroupWithChatId(Long chatId) {
        return telegramGroupRepository.findByChatId(chatId).isPresent();
    }

}
