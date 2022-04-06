package ua.ozzy.apiback.bitrix;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.ozzy.apiback.bitrix.format.ParamKeysFormatter;
import ua.ozzy.apiback.model.Customer;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.service.CrmService;

@Service
public class BitrixCrmService implements CrmService {

    private static final String ADD_CONTACT_METHOD = "crm.contact.add";
    private static final String ADD_DEAL_METHOD = "crm.deal.add";

    private final ParamKeysFormatter paramKeysFormatter;
    private final RestTemplate restTemplate;

    public BitrixCrmService(ParamKeysFormatter paramKeysFormatter, RestTemplate restTemplate) {
        this.paramKeysFormatter = paramKeysFormatter;
        this.restTemplate = restTemplate;
    }

    @Override
    public void createCustomer(Customer customer) {

    }

    @Override
    public void createDeal(Feedback feedback) {

    }

}
