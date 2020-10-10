package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CustomerDto;
import ua.ozzy.apiback.model.Customer;

@Component
public class CustomerDtoMapper extends DtoMapper<Customer, CustomerDto> {

    public CustomerDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
