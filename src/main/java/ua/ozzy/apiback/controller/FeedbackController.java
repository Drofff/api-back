package ua.ozzy.apiback.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.FeedbackDto;
import ua.ozzy.apiback.mapper.FeedbackDtoMapper;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.service.FeedbackService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    private final FeedbackDtoMapper feedbackDtoMapper;

    public FeedbackController(FeedbackService feedbackService, FeedbackDtoMapper feedbackDtoMapper) {
        this.feedbackService = feedbackService;
        this.feedbackDtoMapper = feedbackDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<FeedbackDto>> getFeedbacks(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackService.getFeedbacks(pageable);
        Page<FeedbackDto> feedbackDtos = feedbacks.map(feedbackDtoMapper::toDto);
        return ok(feedbackDtos);
    }

}
