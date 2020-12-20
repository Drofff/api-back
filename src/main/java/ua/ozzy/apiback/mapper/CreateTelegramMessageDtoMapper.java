package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateTelegramMessageDto;
import ua.ozzy.apiback.model.TelegramMessage;

@Component
public class CreateTelegramMessageDtoMapper extends DtoMapper<TelegramMessage, CreateTelegramMessageDto> {

    public CreateTelegramMessageDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
