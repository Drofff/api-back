package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.TelegramGroup;

import java.util.List;

public interface TelegramGroupService {

    TelegramGroup getTelegramGroupById(String id);

    List<TelegramGroup> getTelegramGroups();

    void createTelegramGroup(TelegramGroup telegramGroup);

    void deleteTelegramGroup(TelegramGroup telegramGroup);

}
