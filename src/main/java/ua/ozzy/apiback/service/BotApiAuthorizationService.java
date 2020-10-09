package ua.ozzy.apiback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.SystemUser;

import java.util.Optional;

@Service
public class BotApiAuthorizationService implements AuthorizationService {

    private static final String SCHEME = "AccessKey";

    private final BotApiInfoService botApiInfoService;
    private final PasswordEncoder passwordEncoder;

    public BotApiAuthorizationService(BotApiInfoService botApiInfoService, PasswordEncoder passwordEncoder) {
        this.botApiInfoService = botApiInfoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean usesScheme(String tokenScheme) {
        return SCHEME.equals(tokenScheme);
    }

    @Override
    public Optional<SystemUser> getTokenOwner(String authorizationToken) {
        String accessKeyHashed = passwordEncoder.encode(authorizationToken);
        return botApiInfoService.getBotApiInfoByAccessKeyHash(accessKeyHashed).map(botApi -> botApi);
    }

    @Override
    public String getTokenForUser(SystemUser systemUser) {
        throw new UnsupportedOperationException("Bot API access keys aren't stored in a clear way");
    }

}
