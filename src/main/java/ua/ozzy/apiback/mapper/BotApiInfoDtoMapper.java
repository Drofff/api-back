package ua.ozzy.apiback.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.BotApiInfoDto;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.model.TelegramGroup;

@Component
public class BotApiInfoDtoMapper extends DtoMapper<BotApiInfo, BotApiInfoDto> {

    private final TypeMap<BotApiInfo, BotApiInfoDto> toDtoTypeMap;

    public BotApiInfoDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
        this.toDtoTypeMap = modelMapper.createTypeMap(BotApiInfo.class, BotApiInfoDto.class)
                .addMappings(activeGroupMapping());
    }

    private ExpressionMap<BotApiInfo, BotApiInfoDto> activeGroupMapping() {
        return context -> context.when(mc -> mc.getSource() != null)
                .using(telegramGroupIdExtractor())
                .map(BotApiInfo::getActiveGroup, BotApiInfoDto::setActiveGroup);
    }

    private Converter<TelegramGroup, String> telegramGroupIdExtractor() {
        return ctx -> ctx.getSource().getId();
    }

    @Override
    public BotApiInfoDto toDto(BotApiInfo entity) {
        return toDtoTypeMap.map(entity);
    }

}
