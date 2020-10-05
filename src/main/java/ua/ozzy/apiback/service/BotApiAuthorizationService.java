package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.SystemUser;
import ua.ozzy.apiback.util.CryptoUtil;

import java.util.Optional;

@Service
public class BotApiAuthorizationService implements AuthorizationService {

    private static final String SCHEME = "AccessKey";

    private final BotApiInfoService botApiInfoService;

    public BotApiAuthorizationService(BotApiInfoService botApiInfoService) {
        this.botApiInfoService = botApiInfoService;
    }

    @Override
    public boolean usesScheme(String tokenScheme) {
        return SCHEME.equals(tokenScheme);
    }

    @Override
    public Optional<SystemUser> getTokenOwner(String authorizationToken) {
        String accessKeyHashed = CryptoUtil.hashStr(authorizationToken);
        return botApiInfoService.getBotApiInfoByAccessKeyHash(accessKeyHashed).map(botApi -> botApi);
    }

    @Override
    public String getTokenForUser(SystemUser systemUser) {
        throw new UnsupportedOperationException("Bot API access keys aren't stored in a clear way");
    }

}
