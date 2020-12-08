package ua.ozzy.apiback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.configuration.properties.JwtProperties;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Admin;
import ua.ozzy.apiback.model.SystemUser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
@Primary
public class JwtAuthorizationService implements AuthorizationService {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthorizationService.class);

    private static final String SCHEME = "Bearer";

    private static final String USERNAME_CLAIM = "username";

    private final JwtProperties jwtProperties;

    public JwtAuthorizationService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public boolean usesScheme(String tokenScheme) {
        return SCHEME.equals(tokenScheme);
    }

    @Override
    public Optional<SystemUser> getTokenOwner(String authorizationToken) {
        try {
            DecodedJWT decodedJWT = jwtVerifier().verify(authorizationToken);
            Admin admin = asAdmin(decodedJWT);
            return Optional.of(admin);
        } catch (JWTVerificationException e) {
            LOG.debug("Error verifying token '{}'", authorizationToken, e);
            return Optional.empty();
        }
    }

    private JWTVerifier jwtVerifier() {
        Algorithm alg = signatureAlgorithm();
        return JWT.require(alg).build();
    }

    private Admin asAdmin(DecodedJWT decodedJWT) {
        String id = decodedJWT.getId();
        String username = decodedJWT.getClaim(USERNAME_CLAIM).asString();
        return new Admin.Builder().id(id)
                .username(username)
                .build();
    }

    @Override
    public String getTokenForUser(SystemUser systemUser) {
        validateNotNull(systemUser, "Can not create a token for a null user");
        validateIsAdmin(systemUser);
        Admin admin = (Admin) systemUser;
        return asJwtToken(admin.getId(), admin.getUsername());
    }

    private void validateIsAdmin(SystemUser sysUser) {
        if (isNotAdmin(sysUser)) {
            throw new ValidationException("Only users of type Admin are supported by the JWT authorization service");
        }
    }

    private boolean isNotAdmin(SystemUser sysUser) {
        return !(sysUser instanceof Admin);
    }

    private String asJwtToken(String id, String username) {
        return JWT.create().withSubject(id)
                .withClaim(USERNAME_CLAIM, username)
                .withExpiresAt(nextExpiresAt())
                .sign(signatureAlgorithm());
    }

    private Date nextExpiresAt() {
        GregorianCalendar gregCalendar = new GregorianCalendar();
        gregCalendar.add(Calendar.DAY_OF_YEAR, jwtProperties.getTimeToLiveDays());
        return gregCalendar.getTime();
    }

    private Algorithm signatureAlgorithm() {
        return Algorithm.HMAC512(jwtProperties.getSecret());
    }

}
