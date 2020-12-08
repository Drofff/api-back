package ua.ozzy.apiback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.enums.ApiStatus;
import ua.ozzy.apiback.exception.ConfigNotSetException;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.AccessKey;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.TelegramGroup;
import ua.ozzy.apiback.repository.BotApiInfoRepository;
import ua.ozzy.apiback.util.DbUtil;

import java.util.List;
import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class BotApiInfoServiceImpl implements BotApiInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(BotApiInfoServiceImpl.class);

    private final BotApiInfoRepository botApiInfoRepository;

    private final AccessKeyService accessKeyService;

    public BotApiInfoServiceImpl(BotApiInfoRepository botApiInfoRepository, AccessKeyService accessKeyService) {
        this.botApiInfoRepository = botApiInfoRepository;
        this.accessKeyService = accessKeyService;
    }

    @Override
    public TelegramGroup getActiveTelegramGroup() {
        BotApiInfo botApiInfo = getFirstBotApiInfo();
        TelegramGroup activeTG = botApiInfo.getActiveGroup();
        return Optional.ofNullable(activeTG)
                .orElseThrow(() -> new ConfigNotSetException("Active telegram group is not currently configured"));
    }

    @Override
    public BotApiInfo getFirstBotApiInfo() {
        List<BotApiInfo> botApis = botApiInfoRepository.findAll();
        if (botApis.isEmpty()) {
            throw new ValidationException("There are no Bot APIs registered in the system");
        }
        LOG.debug("{} bot APIs found", botApis.size());
        return botApis.get(0);
    }

    @Override
    public BotApiInfo getBotApiInfoById(String id) {
        return botApiInfoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Bot API with id '" + id + "' doesn't exist"));
    }

    @Override
    public String generateAccessKeyForBotApi(BotApiInfo botApiInfo) {
        validateNotNull(botApiInfo, "Can't generate an access key for the null Bot API");
        AccessKey accessKey = accessKeyService.generateAccessKey();
        botApiInfo.getAccessKeys().add(accessKey);
        botApiInfoRepository.save(botApiInfo);
        return accessKey.getRawKey();
    }

    @Override
    public BotApiInfo createDefaultBotApiInfo() {
        BotApiInfo botApiInfo = new BotApiInfo();
        botApiInfo.setId(DbUtil.generateId());
        botApiInfo.setStatus(ApiStatus.DOWN);
        botApiInfo.setBotUrl("http://not.set");
        return botApiInfoRepository.save(botApiInfo);
    }

    @Override
    public void updateBotApiInfo(BotApiInfo botApiInfo) {
        validate(botApiInfo, "Bot API info is null");
        validateNotNull(botApiInfo.getId(), "Bot API id is required for the update");
        botApiInfoRepository.save(botApiInfo);
    }

}
