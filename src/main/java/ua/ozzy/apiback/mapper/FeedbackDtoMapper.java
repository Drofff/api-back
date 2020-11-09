package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.FeedbackDto;
import ua.ozzy.apiback.model.Feedback;

@Component
public class FeedbackDtoMapper extends DtoMapper<Feedback, FeedbackDto> {

    public FeedbackDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
