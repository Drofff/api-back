package ua.ozzy.apiback.dto;

public class AuthorizationDto {

    private final String token;

    public AuthorizationDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
