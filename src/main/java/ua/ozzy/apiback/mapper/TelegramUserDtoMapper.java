package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.TelegramUserDto;
import ua.ozzy.apiback.model.TelegramUser;

@Component
public class TelegramUserDtoMapper extends DtoMapper<TelegramUser, TelegramUserDto> {

    public TelegramUserDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
