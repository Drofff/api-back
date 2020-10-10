package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Customer;

public interface CustomerService {

    Customer findOrCreateCustomer(Customer customer);

}
