package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ozzy.apiback.dto.*;
import ua.ozzy.apiback.mapper.CreateStatusDtoMapper;
import ua.ozzy.apiback.mapper.StatusDtoMapper;
import ua.ozzy.apiback.mapper.UpdateStatusDtoMapper;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.service.StatusService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/statuses")
public class StatusController {

    private final StatusService statusService;

    private final StatusDtoMapper statusDtoMapper;
    private final CreateStatusDtoMapper createStatusDtoMapper;
    private final UpdateStatusDtoMapper updateStatusDtoMapper;

    public StatusController(StatusService statusService, StatusDtoMapper statusDtoMapper,
                            CreateStatusDtoMapper createStatusDtoMapper, UpdateStatusDtoMapper updateStatusDtoMapper) {
        this.statusService = statusService;
        this.statusDtoMapper = statusDtoMapper;
        this.createStatusDtoMapper = createStatusDtoMapper;
        this.updateStatusDtoMapper = updateStatusDtoMapper;
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

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> createStatus(@RequestBody CreateStatusDto createStatusDto) {
        Status status = createStatusDtoMapper.toEntity(createStatusDto);
        statusService.createStatus(status);
        return ok(new MessageDto("Successfully created status '" + status.getName() + "'"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> updateStatus(@PathVariable String id, @RequestBody UpdateStatusDto updateStatusDto) {
        Status status = updateStatusDtoMapper.toEntity(updateStatusDto);
        status.setId(id);
        statusService.updateStatus(status);
        return ok(new MessageDto("Successfully updated status '" + status.getName() + "'"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> deleteStatus(@PathVariable String id) {
        Status status = statusService.getStatusById(id);
        statusService.deleteStatus(status);
        return ok(new MessageDto("Successfully deleted status '" + status.getName() + "'"));
    }

}
