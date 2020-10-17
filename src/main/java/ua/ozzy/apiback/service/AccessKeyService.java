package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.AccessKey;

public interface AccessKeyService {

    AccessKey generateAccessKey();

    void invalidateAllKeys();

}
