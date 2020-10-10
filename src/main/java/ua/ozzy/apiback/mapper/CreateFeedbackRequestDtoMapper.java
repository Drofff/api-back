package ua.ozzy.apiback.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.ozzy.apiback.dto.CreateFeedbackRequestDto;
import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.model.Feedback;

@Component
public class CreateFeedbackRequestDtoMapper extends DtoMapper<Feedback, CreateFeedbackRequestDto> {

    private final CreateFeedbackRequestCustomerDtoMapper createFeedbackRequestCustomerDtoMapper;

    public CreateFeedbackRequestDtoMapper(ModelMapper modelMapper,
                                          CreateFeedbackRequestCustomerDtoMapper createFeedbackRequestCustomerDtoMapper) {
        super(modelMapper);
        this.createFeedbackRequestCustomerDtoMapper = createFeedbackRequestCustomerDtoMapper;
    }

    @Override
    public Feedback toEntity(CreateFeedbackRequestDto dto) {
        Feedback feedback = getModelMapper().map(dto, Feedback.class);
        Customer customer = createFeedbackRequestCustomerDtoMapper.toEntity(dto.getCustomer());
        feedback.setCustomer(customer);
        return feedback;
    }

}
