package ua.ozzy.apiback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ua.ozzy.apiback.configuration.properties.BotApiProperties;
import ua.ozzy.apiback.dto.UpdateFeedbackInBotApiDto;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.model.StatusReport;
import ua.ozzy.apiback.model.TelegramUser;

import java.util.List;
import java.util.Map;

@Service
public class HttpBotApiUpdateService implements BotApiUpdateService {

    private static final Logger LOG = LoggerFactory.getLogger(HttpBotApiUpdateService.class);

    private final BotApiProperties botApiProperties;

    private final RestTemplate restTemplate;

    public HttpBotApiUpdateService(BotApiProperties botApiProperties, RestTemplate restTemplate) {
        this.botApiProperties = botApiProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendFeedbackUpdate(Feedback feedback) {
        try {
            String url = botApiProperties.getFeedbackUpdateUrl();
            UpdateFeedbackInBotApiDto feedbackDto = asDto(feedback);
            String accessKey = botApiProperties.getAccessKey();
            Map<String, String> queryParams = Map.of("accessKey", accessKey);
            restTemplate.put(url, feedbackDto, queryParams);
        } catch (RestClientException e) {
            throw new ApiBackException(e);
        }
    }

    private UpdateFeedbackInBotApiDto asDto(Feedback feedback) {
        Status status = feedback.getStatus();
        TelegramUser assignedUser = feedback.getAssignedUser();
        return new UpdateFeedbackInBotApiDto(status.getId(), assignedUser.getId());
    }

    @Override
    public void sendStatusReports(List<StatusReport> statusReports) {
        LOG.info("Should have sent {} status reports", statusReports.size());
    }

}