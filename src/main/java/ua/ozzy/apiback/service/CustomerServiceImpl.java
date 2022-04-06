package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.repository.CustomerRepository;
import ua.ozzy.apiback.util.DbUtil;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final PhoneNumberCountryCodeService phoneNumberCountryCodeService;
    private final CrmService crmService;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(PhoneNumberCountryCodeService phoneNumberCountryCodeService,
                               CrmService crmService, CustomerRepository customerRepository) {
        this.phoneNumberCountryCodeService = phoneNumberCountryCodeService;
        this.crmService = crmService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findOrCreateCustomer(Customer customer) {
        validateNotNull(customer, "Customer is null");
        // currently only Ukraine is supported, so country code can be removed
        truncateCustomerPhoneNumberIfNeeded(customer);
        return customerRepository.findByPhoneNumber(customer.getPhoneNumber())
                .orElseGet(() -> createCustomer(customer));
    }

    private void truncateCustomerPhoneNumberIfNeeded(Customer customer) {
        String phoneNum = customer.getPhoneNumber();
        validateNotNull(phoneNum, "Phone number should be provided");
        if (phoneNumberCountryCodeService.startsWithCountryCode(phoneNum)) {
            phoneNum = phoneNumberCountryCodeService.removeCountryCode(phoneNum);
            customer.setPhoneNumber(phoneNum);
        }
    }

    private Customer createCustomer(Customer customer) {
        validate(customer, "Customer should not be null");
        customer.setId(DbUtil.generateId());
        customerRepository.save(customer);
        crmService.createCustomer(customer);
        return customer;
    }

}
