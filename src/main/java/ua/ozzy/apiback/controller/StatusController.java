package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.service.StatusService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('BOT_API', 'ADMIN')")
    public ResponseEntity<List<Status>> getStatuses() {
        return ok(statusService.getStatuses());
    }

}
