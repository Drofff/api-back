package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.ViewTelegramGroupDto;
import ua.ozzy.apiback.model.TelegramGroup;

@Component
public class ViewTelegramGroupDtoMapper extends DtoMapper<TelegramGroup, ViewTelegramGroupDto> {

    public ViewTelegramGroupDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
