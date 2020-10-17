package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateTelegramGroupDto;
import ua.ozzy.apiback.model.TelegramGroup;

@Component
public class CreateTelegramGroupDtoMapper extends DtoMapper<TelegramGroup, CreateTelegramGroupDto> {

    public CreateTelegramGroupDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
