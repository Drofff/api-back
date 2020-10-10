package ua.ozzy.apiback.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.ozzy.apiback.dto.*;
import ua.ozzy.apiback.mapper.CreateFeedbackRequestDtoMapper;
import ua.ozzy.apiback.mapper.FeedbackDtoMapper;
import ua.ozzy.apiback.mapper.UpdateFeedbackRequestTelegramUserDtoMapper;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.service.FeedbackService;
import ua.ozzy.apiback.service.TelegramUserService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final TelegramUserService telegramUserService;

    private final FeedbackDtoMapper feedbackDtoMapper;
    private final CreateFeedbackRequestDtoMapper createFeedbackRequestDtoMapper;
    private final UpdateFeedbackRequestTelegramUserDtoMapper updateFeedbackRequestTelegramUserDtoMapper;

    public FeedbackController(FeedbackService feedbackService, TelegramUserService telegramUserService,
                              FeedbackDtoMapper feedbackDtoMapper, CreateFeedbackRequestDtoMapper createFeedbackRequestDtoMapper,
                              UpdateFeedbackRequestTelegramUserDtoMapper updateFeedbackRequestTelegramUserDtoMapper) {
        this.feedbackService = feedbackService;
        this.telegramUserService = telegramUserService;
        this.feedbackDtoMapper = feedbackDtoMapper;
        this.createFeedbackRequestDtoMapper = createFeedbackRequestDtoMapper;
        this.updateFeedbackRequestTelegramUserDtoMapper = updateFeedbackRequestTelegramUserDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<FeedbackDto>> getFeedbacks(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackService.getFeedbacks(pageable);
        Page<FeedbackDto> feedbackDtos = feedbacks.map(feedbackDtoMapper::toDto);
        return ok(feedbackDtos);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('BOT_API')")
    public ResponseEntity<MessageDto> createFeedback(@RequestBody CreateFeedbackRequestDto createFbReqDto) {
        Feedback feedback = createFeedbackRequestDtoMapper.toEntity(createFbReqDto);
        feedbackService.createFeedback(feedback);
        return ok(new MessageDto("Feedback has been successfully saved"));
    }

    @PutMapping("/{id}-as-bot-api")
    @PreAuthorize("hasAuthority('BOT_API')")
    public ResponseEntity<MessageDto> updateFeedbackAsBotApi(@PathVariable String id,
                                                             @RequestBody BotApiUpdateFeedbackRequestDto reqDto) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        feedback.setStatus(reqDto.getStatus());
        TelegramUser assignedUser = asTelegramUser(reqDto.getAssignedUser());
        feedback.setAssignedUser(assignedUser);
        feedbackService.updateFeedbackForRequester(feedback, reqDto.getRequesterId());
        return ok(new MessageDto("Feedback has been successfully updated"));
    }

    private TelegramUser asTelegramUser(UpdateFeedbackRequestTelegramUserDto telUsrDto) {
        TelegramUser telegramUser = updateFeedbackRequestTelegramUserDtoMapper.toEntity(telUsrDto);
        return telegramUserService.findOrCreateTelegramUser(telegramUser);
    }

    @PutMapping("/{id}-as-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> updateFeedbackAsAdmin(@PathVariable String id,
                                                            @RequestBody AdminUpdateFeedbackRequestDto reqDto) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        feedback.setStatus(reqDto.getStatus());
        String assignedUserId = reqDto.getAssignedUserId();
        TelegramUser assignedUser = telegramUserService.getTelegramUserById(assignedUserId);
        feedback.setAssignedUser(assignedUser);
        feedbackService.updateFeedback(feedback);
        return ok(new MessageDto("Feedback has been successfully updated"));
    }

}
