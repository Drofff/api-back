package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ua.ozzy.apiback.configuration.properties.BotApiProperties;
import ua.ozzy.apiback.dto.UpdateFeedbackInBotApiDto;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.Status;

import java.util.Map;

@Service
public class HttpBotApiUpdateService implements BotApiUpdateService {

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
        return new UpdateFeedbackInBotApiDto(status.getId(), feedback.getAssignedUserId());
    }

}
