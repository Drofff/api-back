package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.model.Feedback;

public interface CrmService {

    void createCustomer(Customer customer);

    void createDeal(Feedback feedback);

}
