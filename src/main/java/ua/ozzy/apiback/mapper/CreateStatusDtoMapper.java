package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateStatusDto;
import ua.ozzy.apiback.model.Status;

@Component
public class CreateStatusDtoMapper extends DtoMapper<Status, CreateStatusDto> {

    public CreateStatusDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
