package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ozzy.apiback.dto.FindStatusDto;
import ua.ozzy.apiback.dto.StatusDto;
import ua.ozzy.apiback.mapper.StatusDtoMapper;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.service.StatusService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/statuses")
public class StatusController {

    private final StatusService statusService;

    private final StatusDtoMapper statusDtoMapper;

    public StatusController(StatusService statusService, StatusDtoMapper statusDtoMapper) {
        this.statusService = statusService;
        this.statusDtoMapper = statusDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('BOT_API', 'ADMIN')")
    public ResponseEntity<List<StatusDto>> getStatuses(@RequestParam(required = false) FindStatusDto searchCriteria) {
        List<Status> statuses = searchCriteria == null
                ? statusService.getStatuses()
                : statusService.findStatuses(searchCriteria);
        return ok(statusDtoMapper.toDtos(statuses));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('BOT_API', 'ADMIN')")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable String id) {
        Status status = statusService.getStatusById(id);
        return ok(statusDtoMapper.toDto(status));
    }

}
