package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.TelegramGroup;

import java.util.Optional;

public interface BotApiInfoService {

    TelegramGroup getActiveTelegramGroup();

    BotApiInfo getFirstBotApiInfo();

    BotApiInfo getBotApiInfoById(String id);

    Optional<BotApiInfo> getBotApiInfoByAccessKeyHash(String accessKeyHash);

    String generateAccessKeyForBotApi(BotApiInfo botApiInfo);

    BotApiInfo createDefaultBotApiInfo();

    void updateBotApiInfo(BotApiInfo botApiInfo);

}
