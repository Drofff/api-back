package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.SystemUser;

public interface AuthenticationService {

    SystemUser authenticate(String username, String password);

}
