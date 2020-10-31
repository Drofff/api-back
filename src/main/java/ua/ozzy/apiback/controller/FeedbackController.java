package ua.ozzy.apiback.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
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
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.model.TelegramUser;
import ua.ozzy.apiback.service.FeedbackService;
import ua.ozzy.apiback.service.StatusService;
import ua.ozzy.apiback.service.TelegramUserService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final StatusService statusService;
    private final TelegramUserService telegramUserService;

    private final FeedbackDtoMapper feedbackDtoMapper;
    private final CreateFeedbackRequestDtoMapper createFeedbackRequestDtoMapper;
    private final UpdateFeedbackRequestTelegramUserDtoMapper updateFeedbackRequestTelegramUserDtoMapper;

    public FeedbackController(FeedbackService feedbackService, StatusService statusService, TelegramUserService telegramUserService,
                              FeedbackDtoMapper feedbackDtoMapper, CreateFeedbackRequestDtoMapper createFeedbackRequestDtoMapper,
                              UpdateFeedbackRequestTelegramUserDtoMapper updateFeedbackRequestTelegramUserDtoMapper) {
        this.feedbackService = feedbackService;
        this.statusService = statusService;
        this.telegramUserService = telegramUserService;
        this.feedbackDtoMapper = feedbackDtoMapper;
        this.createFeedbackRequestDtoMapper = createFeedbackRequestDtoMapper;
        this.updateFeedbackRequestTelegramUserDtoMapper = updateFeedbackRequestTelegramUserDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "page", value = "Number of the desired page starting from 0", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Size of the response page", paramType = "query")
    })
    public ResponseEntity<Page<FeedbackDto>> getFeedbacks(@ApiParam(hidden = true) Pageable pageable) {
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
    public ResponseEntity<FeedbackDto> updateFeedbackAsBotApi(@PathVariable String id,
                                                             @RequestBody BotApiUpdateFeedbackRequestDto updateDto) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        applyFeedbackUpdate(feedback, updateDto);
        Feedback updatedFeedback = feedbackService.updateFeedbackForRequester(feedback, updateDto.getRequesterId());
        return ok(feedbackDtoMapper.toDto(updatedFeedback));
    }

    private void applyFeedbackUpdate(Feedback feedback, BotApiUpdateFeedbackRequestDto update) {
        Status status = statusService.getStatusById(update.getStatusId());
        feedback.setStatus(status);
        TelegramUser assignedUser = asTelegramUser(update.getAssignedUser());
        feedback.setAssignedUser(assignedUser);
    }

    private TelegramUser asTelegramUser(UpdateFeedbackRequestTelegramUserDto telUsrDto) {
        TelegramUser telegramUser = updateFeedbackRequestTelegramUserDtoMapper.toEntity(telUsrDto);
        return telegramUserService.findOrCreateTelegramUser(telegramUser);
    }

    @PutMapping("/{id}-as-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageDto> updateFeedbackAsAdmin(@PathVariable String id,
                                                            @RequestBody AdminUpdateFeedbackRequestDto updateDto) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        applyFeedbackUpdate(feedback, updateDto);
        feedbackService.updateFeedback(feedback);
        return ok(new MessageDto("Feedback has been successfully updated"));
    }

    private void applyFeedbackUpdate(Feedback feedback, AdminUpdateFeedbackRequestDto update) {
        Status status = statusService.getStatusById(update.getStatusId());
        feedback.setStatus(status);
        String assignedUserId = update.getAssignedUserId();
        TelegramUser assignedUser = telegramUserService.getTelegramUserById(assignedUserId);
        feedback.setAssignedUser(assignedUser);
    }

}
