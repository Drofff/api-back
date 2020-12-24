package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.StatusReportForBotApiDto;
import ua.ozzy.apiback.model.StatusReport;

@Component
public class StatusReportForBotApiDtoMapper extends DtoMapper<StatusReport, StatusReportForBotApiDto> {

    public StatusReportForBotApiDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
