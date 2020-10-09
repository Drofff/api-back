package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.AuthenticationDto;
import ua.ozzy.apiback.dto.AuthorizationDto;
import ua.ozzy.apiback.model.SystemUser;
import ua.ozzy.apiback.service.AuthenticationService;
import ua.ozzy.apiback.service.AuthorizationService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;

    public AuthController(AuthenticationService authenticationService, AuthorizationService authorizationService) {
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<AuthorizationDto> authenticate(@RequestBody AuthenticationDto authDto) {
        SystemUser sysUser = authenticationService.authenticate(authDto.getUsername(), authDto.getPassword());
        String authzToken = authorizationService.getTokenForUser(sysUser);
        return ok(new AuthorizationDto(authzToken));
    }

}
