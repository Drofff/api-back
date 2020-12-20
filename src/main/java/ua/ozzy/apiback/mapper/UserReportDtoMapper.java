package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.UserReportDto;
import ua.ozzy.apiback.model.UserReport;

@Component
public class UserReportDtoMapper extends DtoMapper<UserReport, UserReportDto> {

    public UserReportDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
