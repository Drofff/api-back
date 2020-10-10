package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.UpdateFeedbackRequestTelegramUserDto;
import ua.ozzy.apiback.model.TelegramUser;

@Component
public class UpdateFeedbackRequestTelegramUserDtoMapper extends DtoMapper<TelegramUser, UpdateFeedbackRequestTelegramUserDto> {

    public UpdateFeedbackRequestTelegramUserDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
