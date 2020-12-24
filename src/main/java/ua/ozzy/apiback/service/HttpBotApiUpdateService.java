package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ua.ozzy.apiback.configuration.properties.BotApiProperties;
import ua.ozzy.apiback.dto.StatusReportForBotApiDto;
import ua.ozzy.apiback.dto.UpdateFeedbackInBotApiDto;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.mapper.StatusReportForBotApiDtoMapper;
import ua.ozzy.apiback.mapper.UpdateFeedbackInBotApiDtoMapper;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.StatusReport;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class HttpBotApiUpdateService implements BotApiUpdateService {

    private final BotApiProperties botApiProperties;

    private final RestTemplate restTemplate;

    private final UpdateFeedbackInBotApiDtoMapper updateFeedbackInBotApiDtoMapper;
    private final StatusReportForBotApiDtoMapper statusReportForBotApiDtoMapper;

    public HttpBotApiUpdateService(BotApiProperties botApiProperties, RestTemplate restTemplate,
                                   UpdateFeedbackInBotApiDtoMapper updateFeedbackInBotApiDtoMapper,
                                   StatusReportForBotApiDtoMapper statusReportForBotApiDtoMapper) {
        this.botApiProperties = botApiProperties;
        this.restTemplate = restTemplate;
        this.updateFeedbackInBotApiDtoMapper = updateFeedbackInBotApiDtoMapper;
        this.statusReportForBotApiDtoMapper = statusReportForBotApiDtoMapper;
    }

    @Override
    public void sendFeedbackUpdate(Feedback feedback) {
        try {
            String url = botApiProperties.getFeedbackUpdateUrl();
            UpdateFeedbackInBotApiDto feedbackDto = updateFeedbackInBotApiDtoMapper.toDto(feedback);
            restTemplate.put(url, feedbackDto, accessKeyQueryParam());
        } catch (RestClientException e) {
            throw new ApiBackException(e);
        }
    }

    @Override
    public void sendStatusReports(List<StatusReport> statusReports) {
        try {
            String url = botApiProperties.getStatusReportUrl();
            List<StatusReportForBotApiDto> reportsForBotApi = asStatusReportsForBotApi(statusReports);
            restTemplate.postForObject(url, reportsForBotApi, Object.class, accessKeyQueryParam());
        } catch (RestClientException e) {
            throw new ApiBackException(e);
        }
    }

    private List<StatusReportForBotApiDto> asStatusReportsForBotApi(List<StatusReport> statusReports) {
        return statusReports.stream()
                .map(statusReportForBotApiDtoMapper::toDto)
                .collect(toList());
    }

    private Map<String, String> accessKeyQueryParam() {
        String accessKey = botApiProperties.getAccessKey();
        return Map.of("accessKey", accessKey);
    }

}