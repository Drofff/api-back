package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateFeedbackRequestCustomerDto;
import ua.ozzy.apiback.model.Customer;

@Component
public class CreateFeedbackRequestCustomerDtoMapper extends DtoMapper<Customer, CreateFeedbackRequestCustomerDto> {

    public CreateFeedbackRequestCustomerDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

}
