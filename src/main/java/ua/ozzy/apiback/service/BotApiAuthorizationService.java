package ua.ozzy.apiback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.SystemUser;
import ua.ozzy.apiback.repository.BotApiInfoRepository;

import java.util.Optional;

@Service
public class BotApiAuthorizationService implements AuthorizationService {

    private static final String SCHEME = "AccessKey";

    private final BotApiInfoRepository botApiInfoRepository;

    private final PasswordEncoder passwordEncoder;

    public BotApiAuthorizationService(BotApiInfoRepository botApiInfoRepository, PasswordEncoder passwordEncoder) {
        this.botApiInfoRepository = botApiInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean usesScheme(String tokenScheme) {
        return SCHEME.equals(tokenScheme);
    }

    @Override
    public Optional<SystemUser> getTokenOwner(String accessKey) {
        String accessKeyHashed = passwordEncoder.encode(accessKey);
        return botApiInfoRepository.findByAccessKeyHash(accessKeyHashed).map(botApi -> botApi);
    }

    @Override
    public String getTokenForUser(SystemUser systemUser) {
        throw new UnsupportedOperationException("Bot API access keys aren't stored in a raw format");
    }

}
