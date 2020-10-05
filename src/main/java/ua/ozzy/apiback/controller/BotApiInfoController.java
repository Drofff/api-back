package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.BotApiInfoDto;
import ua.ozzy.apiback.mapper.BotApiInfoDtoMapper;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.service.BotApiInfoService;
import ua.ozzy.apiback.service.FeedbackService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/bot-api/info")
public class BotApiInfoController {

    private final BotApiInfoService botApiInfoService;
    private final FeedbackService feedbackService;

    private final BotApiInfoDtoMapper botApiInfoDtoMapper;

    public BotApiInfoController(BotApiInfoService botApiInfoService, FeedbackService feedbackService,
                                BotApiInfoDtoMapper botApiInfoDtoMapper) {
        this.botApiInfoService = botApiInfoService;
        this.feedbackService = feedbackService;
        this.botApiInfoDtoMapper = botApiInfoDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BotApiInfoDto> getBotApiInfo() {
        BotApiInfo botApiInfo = botApiInfoService.getFirstBotApiInfo();
        BotApiInfoDto botApiInfoDto = botApiInfoDtoMapper.toDto(botApiInfo);
        feedbackService.getLatestFeedback()
                .map(Feedback::getDateTime)
                .ifPresent(botApiInfoDto::setLastFeedbackDateTime);
        return ok(botApiInfoDto);
    }

}
