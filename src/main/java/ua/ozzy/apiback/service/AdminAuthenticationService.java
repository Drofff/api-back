package ua.ozzy.apiback.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.AuthenticationException;
import ua.ozzy.apiback.model.Admin;
import ua.ozzy.apiback.model.SystemUser;

@Service
public class AdminAuthenticationService implements AuthenticationService {

    private final AuthenticationManager authManager;

    public AdminAuthenticationService(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public SystemUser authenticate(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authManager.authenticate(authToken);
            return (Admin) auth.getPrincipal();
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }

}
