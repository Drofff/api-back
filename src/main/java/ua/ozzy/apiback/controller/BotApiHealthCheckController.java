package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ozzy.apiback.dto.BotApiHealthCheckDto;
import ua.ozzy.apiback.dto.BotApiHealthCheckRequestDto;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.mapper.BotApiHealthCheckDtoMapper;
import ua.ozzy.apiback.model.BotApiHealthCheck;
import ua.ozzy.apiback.model.BotApiInfo;
import ua.ozzy.apiback.service.BotApiHealthCheckService;
import ua.ozzy.apiback.util.AuthUtil;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v0/bot-api/health-checks")
public class BotApiHealthCheckController {

    private final BotApiHealthCheckService botApiHealthCheckService;

    private final BotApiHealthCheckDtoMapper botApiHealthCheckDtoMapper;

    public BotApiHealthCheckController(BotApiHealthCheckService botApiHealthCheckService,
                                       BotApiHealthCheckDtoMapper botApiHealthCheckDtoMapper) {
        this.botApiHealthCheckService = botApiHealthCheckService;
        this.botApiHealthCheckDtoMapper = botApiHealthCheckDtoMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BotApiHealthCheckDto>> getBotApiHealthChecks(BotApiHealthCheckRequestDto healthCheckReq) {
        List<BotApiHealthCheck> healthChecks = botApiHealthCheckService
                .getHealthChecksOfBotApiForPeriod(healthCheckReq.getBotApiId(), healthCheckReq.getPeriodMinutes());
        List<BotApiHealthCheckDto> healthCheckDtos = botApiHealthCheckDtoMapper.toDtos(healthChecks);
        return ok(healthCheckDtos);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('BOT_API')")
    public ResponseEntity<MessageDto> submitHealthCheck() {
        BotApiInfo botApiInfo = AuthUtil.getCurrentBotApiInfo();
        botApiHealthCheckService.submitHealthCheck(botApiInfo.getId());
        return ok(new MessageDto("Health check has been successfully submitted"));
    }

}
