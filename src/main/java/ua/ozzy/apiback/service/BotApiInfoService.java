package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.BotApiInfo;

import java.util.Optional;

public interface BotApiInfoService {

    BotApiInfo getFirstBotApiInfo();

    BotApiInfo getBotApiInfoById(String id);

    Optional<BotApiInfo> getBotApiInfoByAccessKeyHash(String accessKeyHash);

    String generateAccessKeyForBotApi(BotApiInfo botApiInfo);

    BotApiInfo createDefaultBotApiInfo();

    void updateBotApiInfo(BotApiInfo botApiInfo);

}
