package ua.ozzy.apiback.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.ozzy.apiback.exception.AuthorizationException;
import ua.ozzy.apiback.model.SystemUser;
import ua.ozzy.apiback.service.AuthorizationService;
import ua.ozzy.apiback.util.TokenSchemeUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AuthorizationFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final List<AuthorizationService> authzServices;

    public AuthorizationFilter(List<AuthorizationService> authzServices) {
        this.authzServices = authzServices;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            authorizeRequest((HttpServletRequest) servletRequest);
        } catch (AuthorizationException e) {
            LOG.trace("Error authorizing request", e);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void authorizeRequest(HttpServletRequest req) {
        String authzToken = getAuthorizationTokenFromHeader(req);
        SystemUser owner = findOwnerOfToken(authzToken);
        setUserIntoContext(owner);
    }

    private String getAuthorizationTokenFromHeader(HttpServletRequest req) {
        String tokenHeader = req.getHeader(AUTHORIZATION_HEADER);
        return Optional.ofNullable(tokenHeader)
                .orElseThrow(() -> new AuthorizationException("Missing authorization token"));
    }

    private SystemUser findOwnerOfToken(String token) {
        String scheme = TokenSchemeUtil.getSchemeOfToken(token);
        AuthorizationService authzService = findAuthorizationServiceForScheme(scheme);
        String tokenWithoutScheme = TokenSchemeUtil.removeSchemeFromToken(token);
        return authzService.getTokenOwner(tokenWithoutScheme)
                .orElseThrow(() -> new AuthorizationException("Invalid authorization token"));
    }

    private AuthorizationService findAuthorizationServiceForScheme(String scheme) {
        return authzServices.stream()
                .filter(as -> as.usesScheme(scheme))
                .findFirst()
                .orElseThrow(() -> new AuthorizationException("Unknown token scheme '" + scheme + "'"));
    }

    private void setUserIntoContext(SystemUser user) {
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(user.toAuthentication());
    }

}
