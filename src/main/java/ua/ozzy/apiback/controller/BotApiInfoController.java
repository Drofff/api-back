package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ozzy.apiback.dto.*;
import ua.ozzy.apiback.mapper.BotApiInfoDtoMapper;
import ua.ozzy.apiback.mapper.ViewTelegramGroupDtoMapper;
import ua.ozzy.apiback.model.AccessKey;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramGroup;
import ua.ozzy.apiback.service.AccessKeyService;
import ua.ozzy.apiback.service.BotApiInfoService;
import ua.ozzy.apiback.service.FeedbackService;
import ua.ozzy.apiback.service.TelegramGroupService;
import ua.ozzy.apiback.util.MappingUtil;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/bot-api")
public class BotApiInfoController {

    private final BotApiInfoService botApiInfoService;
    private final FeedbackService feedbackService;
    private final TelegramGroupService telegramGroupService;
    private final AccessKeyService accessKeyService;

    private final BotApiInfoDtoMapper botApiInfoDtoMapper;
    private final ViewTelegramGroupDtoMapper viewTelegramGroupDtoMapper;

    public BotApiInfoController(BotApiInfoService botApiInfoService, FeedbackService feedbackService,
                                TelegramGroupService telegramGroupService, AccessKeyService accessKeyService,
                                BotApiInfoDtoMapper botApiInfoDtoMapper, ViewTelegramGroupDtoMapper viewTelegramGroupDtoMapper) {
        this.botApiInfoService = botApiInfoService;
        this.feedbackService = feedbackService;
        this.telegramGroupService = telegramGroupService;
        this.accessKeyService = accessKeyService;
        this.botApiInfoDtoMapper = botApiInfoDtoMapper;
        this.viewTelegramGroupDtoMapper = viewTelegramGroupDtoMapper;
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BotApiInfoDto> getBotApiInfo() {
        BotApiInfo botApiInfo = botApiInfoService.getFirstBotApiInfo();
        BotApiInfoDto botApiInfoDto = botApiInfoDtoMapper.toDto(botApiInfo);
        feedbackService.getLatestFeedback()
                .map(Feedback::getDateTime)
                .ifPresent(botApiInfoDto::setLastFeedbackDateTime);
        return ok(botApiInfoDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<NewBotApiInfoDto> createDefaultBotApiInfo() {
        BotApiInfo botApiInfo = botApiInfoService.createDefaultBotApiInfo();
        return ok(new NewBotApiInfoDto(botApiInfo.getId()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> updateBotApiInfo(@PathVariable String id,
                                                       @RequestBody UpdateBotApiInfoDto updateBotApiInfoDto) {
        BotApiInfo botApiInfo = botApiInfoService.getBotApiInfoById(id);
        MappingUtil.copyMatchingFields(updateBotApiInfoDto, botApiInfo);
        TelegramGroup tg = telegramGroupService.getTelegramGroupById(updateBotApiInfoDto.getTelegramGroupId());
        botApiInfo.setActiveGroup(tg);
        botApiInfoService.updateBotApiInfo(botApiInfo);
        return ok(new MessageDto("Bot API info has been successfully updated"));
    }

    @PostMapping("/{id}/access-keys")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AccessKeyDto> generateAccessKey(@PathVariable String id) {
        BotApiInfo botApiInfo = botApiInfoService.getBotApiInfoById(id);
        AccessKey accessKey = accessKeyService.generateAccessKey();
        botApiInfo.getAccessKeys().add(accessKey);
        botApiInfoService.updateBotApiInfo(botApiInfo);
        return ok(new AccessKeyDto(accessKey.getRawKey()));
    }

    @GetMapping("/active-group")
    @PreAuthorize("hasAnyAuthority('BOT_API', 'ADMIN')")
    public ResponseEntity<ViewTelegramGroupDto> getActiveTelegramGroup() {
        TelegramGroup activeTG = botApiInfoService.getActiveTelegramGroup();
        ViewTelegramGroupDto viewTelegramGroupDto = viewTelegramGroupDtoMapper.toDto(activeTG);
        return ok(viewTelegramGroupDto);
    }

}
