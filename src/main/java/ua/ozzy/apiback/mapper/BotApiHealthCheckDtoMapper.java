package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.BotApiHealthCheckDto;
import ua.ozzy.apiback.model.BotApiHealthCheck;

@Component
public class BotApiHealthCheckDtoMapper extends DtoMapper<BotApiHealthCheck, BotApiHealthCheckDto> {

    public BotApiHealthCheckDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
