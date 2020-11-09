package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateFeedbackRequestDto;
import ua.ozzy.apiback.model.Feedback;

@Component
public class CreateFeedbackRequestDtoMapper extends DtoMapper<Feedback, CreateFeedbackRequestDto> {

    public CreateFeedbackRequestDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
