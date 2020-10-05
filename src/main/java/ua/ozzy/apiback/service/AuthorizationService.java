package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.SystemUser;

import java.util.Optional;

public interface AuthorizationService {

    boolean usesScheme(String tokenScheme);

    Optional<SystemUser> getTokenOwner(String authorizationToken);

    String getTokenForUser(SystemUser systemUser);

}
