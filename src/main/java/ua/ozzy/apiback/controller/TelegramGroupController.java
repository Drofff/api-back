package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ozzy.apiback.dto.CreateTelegramGroupDto;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.dto.ViewTelegramGroupDto;
import ua.ozzy.apiback.mapper.CreateTelegramGroupDtoMapper;
import ua.ozzy.apiback.mapper.ViewTelegramGroupDtoMapper;
import ua.ozzy.apiback.model.TelegramGroup;
import ua.ozzy.apiback.service.TelegramGroupService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/telegram-groups")
public class TelegramGroupController {

    private final TelegramGroupService telegramGroupService;

    private final CreateTelegramGroupDtoMapper createTelegramGroupDtoMapper;
    private final ViewTelegramGroupDtoMapper viewTelegramGroupDtoMapper;

    public TelegramGroupController(TelegramGroupService telegramGroupService,
                                   CreateTelegramGroupDtoMapper createTelegramGroupDtoMapper,
                                   ViewTelegramGroupDtoMapper viewTelegramGroupDtoMapper) {
        this.telegramGroupService = telegramGroupService;
        this.createTelegramGroupDtoMapper = createTelegramGroupDtoMapper;
        this.viewTelegramGroupDtoMapper = viewTelegramGroupDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViewTelegramGroupDto>> getTelegramGroups() {
        List<TelegramGroup> telegramGroups = telegramGroupService.getTelegramGroups();
        List<ViewTelegramGroupDto> viewTelegramGroupDtos = viewTelegramGroupDtoMapper.toDtos(telegramGroups);
        return ok(viewTelegramGroupDtos);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('BOT_API', 'ADMIN')")
    public ResponseEntity<MessageDto> createTelegramGroup(@RequestBody CreateTelegramGroupDto createTelegramGroupDto) {
        TelegramGroup tg = createTelegramGroupDtoMapper.toEntity(createTelegramGroupDto);
        telegramGroupService.createTelegramGroup(tg);
        return ok(new MessageDto("Telegram group has been successfully added"));
    }

}
