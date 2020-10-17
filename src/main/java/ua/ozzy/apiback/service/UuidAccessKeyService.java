package ua.ozzy.apiback.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.AccessKey;
import ua.ozzy.apiback.repository.AccessKeyRepository;

import java.util.UUID;

@Service
public class UuidAccessKeyService implements AccessKeyService {

    private final AccessKeyRepository accessKeyRepository;

    private final PasswordEncoder passwordEncoder;

    public UuidAccessKeyService(AccessKeyRepository accessKeyRepository, PasswordEncoder passwordEncoder) {
        this.accessKeyRepository = accessKeyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccessKey generateAccessKey() {
        String randomKey = UUID.randomUUID().toString();
        String keyHash = passwordEncoder.encode(randomKey);
        AccessKey accessKey = new AccessKey(keyHash);
        accessKeyRepository.save(accessKey);
        accessKey.setRawKey(randomKey);
        return accessKey;
    }

    @Override
    public void invalidateAllKeys() {
        accessKeyRepository.deleteAll();
    }

}
