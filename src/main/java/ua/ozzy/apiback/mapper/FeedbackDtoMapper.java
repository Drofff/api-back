package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CustomerDto;
import ua.ozzy.apiback.dto.FeedbackDto;
import ua.ozzy.apiback.dto.StatusDto;
import ua.ozzy.apiback.dto.TelegramUserDto;
import ua.ozzy.apiback.model.Feedback;

@Component
public class FeedbackDtoMapper extends DtoMapper<Feedback, FeedbackDto> {

    private final StatusDtoMapper statusDtoMapper;
    private final CustomerDtoMapper customerDtoMapper;
    private final TelegramUserDtoMapper telegramUserDtoMapper;

    public FeedbackDtoMapper(ModelMapper modelMapper, StatusDtoMapper statusDtoMapper,
                             CustomerDtoMapper customerDtoMapper, TelegramUserDtoMapper telegramUserDtoMapper) {
        super(modelMapper);
        this.statusDtoMapper = statusDtoMapper;
        this.customerDtoMapper = customerDtoMapper;
        this.telegramUserDtoMapper = telegramUserDtoMapper;
    }

    @Override
    public FeedbackDto toDto(Feedback feedback) {
        FeedbackDto feedbackDto = getModelMapper().map(feedback, FeedbackDto.class);
        StatusDto statusDto = statusDtoMapper.toDto(feedback.getStatus());
        feedbackDto.setStatus(statusDto);
        CustomerDto customerDto = customerDtoMapper.toDto(feedback.getCustomer());
        feedbackDto.setCustomer(customerDto);
        TelegramUserDto assignedUserDto = telegramUserDtoMapper.toDto(feedback.getAssignedUser());
        feedbackDto.setAssignedUser(assignedUserDto);
        return feedbackDto;
    }

}
