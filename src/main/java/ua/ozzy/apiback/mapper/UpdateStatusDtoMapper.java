package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.UpdateStatusDto;
import ua.ozzy.apiback.model.Status;

@Component
public class UpdateStatusDtoMapper extends DtoMapper<Status, UpdateStatusDto> {

    public UpdateStatusDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
