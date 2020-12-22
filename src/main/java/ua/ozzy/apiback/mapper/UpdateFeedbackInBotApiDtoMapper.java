package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.UpdateFeedbackInBotApiDto;
import ua.ozzy.apiback.model.Feedback;

@Component
public class UpdateFeedbackInBotApiDtoMapper extends DtoMapper<Feedback, UpdateFeedbackInBotApiDto> {

    public UpdateFeedbackInBotApiDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
