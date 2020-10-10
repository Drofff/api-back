package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.StatusDto;
import ua.ozzy.apiback.model.Status;

@Component
public class StatusDtoMapper extends DtoMapper<Status, StatusDto> {

    public StatusDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
