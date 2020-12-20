package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.CreateTelegramMessageDto;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.dto.TelegramMessageDto;
import ua.ozzy.apiback.mapper.CreateTelegramMessageDtoMapper;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramMessage;
import ua.ozzy.apiback.service.FeedbackService;
import ua.ozzy.apiback.service.TelegramMessageService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/telegram-messages")
public class TelegramMessageController {

    private final TelegramMessageService telegramMessageService;
    private final FeedbackService feedbackService;

    private final CreateTelegramMessageDtoMapper createTelegramMessageDtoMapper;

    public TelegramMessageController(TelegramMessageService telegramMessageService, FeedbackService feedbackService,
                                     CreateTelegramMessageDtoMapper createTelegramMessageDtoMapper) {
        this.telegramMessageService = telegramMessageService;
        this.feedbackService = feedbackService;
        this.createTelegramMessageDtoMapper = createTelegramMessageDtoMapper;
    }

    @GetMapping
    public ResponseEntity<TelegramMessageDto> getTelegramMessageOfFeedback(String feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        TelegramMessage tgMsg = telegramMessageService.getTelegramMessageOfFeedback(feedback);
        return ok(new TelegramMessageDto(tgMsg.getId()));
    }

    @PostMapping
    public ResponseEntity<MessageDto> createTelegramMessage(CreateTelegramMessageDto createTgMsgDto) {
        TelegramMessage tgMsg = asTelegramMessage(createTgMsgDto);
        telegramMessageService.createTelegramMessage(tgMsg);
        return ok(new MessageDto("Telegram message has been successfully created"));
    }

    private TelegramMessage asTelegramMessage(CreateTelegramMessageDto createTgMsgDto) {
        TelegramMessage tgMsg = createTelegramMessageDtoMapper.toEntity(createTgMsgDto);
        Feedback feedback = feedbackService.getFeedbackById(createTgMsgDto.getFeedbackId());
        tgMsg.setFeedback(feedback);
        return tgMsg;
    }

}
