package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.TelegramGroup;

public interface BotApiInfoService {

    TelegramGroup getActiveTelegramGroup();

    BotApiInfo getFirstBotApiInfo();

    BotApiInfo getBotApiInfoById(String id);

    BotApiInfo createDefaultBotApiInfo();

    void updateBotApiInfo(BotApiInfo botApiInfo);

}
