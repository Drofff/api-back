package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.service.AccessKeyService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/access-keys")
public class AccessKeyController {

    private final AccessKeyService accessKeyService;

    public AccessKeyController(AccessKeyService accessKeyService) {
        this.accessKeyService = accessKeyService;
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> invalidateAllKeys() {
        accessKeyService.invalidateAllKeys();
        return ok(new MessageDto("All access keys have been successfully invalidated"));
    }

}
