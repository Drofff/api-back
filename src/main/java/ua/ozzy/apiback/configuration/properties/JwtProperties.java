package ua.ozzy.apiback.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private Integer timeToLiveDays;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getTimeToLiveDays() {
        return timeToLiveDays;
    }

    public void setTimeToLiveDays(Integer timeToLiveDays) {
        this.timeToLiveDays = timeToLiveDays;
    }

}
