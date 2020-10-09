package ua.ozzy.apiback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.repository.BotApiInfoRepository;

import java.util.List;
import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class BotApiInfoServiceImpl implements BotApiInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(BotApiInfoServiceImpl.class);

    private final BotApiInfoRepository botApiInfoRepository;

    public BotApiInfoServiceImpl(BotApiInfoRepository botApiInfoRepository) {
        this.botApiInfoRepository = botApiInfoRepository;
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
    public Optional<BotApiInfo> getBotApiInfoByAccessKeyHash(String accessKeyHash) {
        validateNotNull(accessKeyHash, "Access key should not be null");
        return botApiInfoRepository.findByAccessKeyHash(accessKeyHash);
    }

}
