package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.BotApiHealthCheck;
import ua.ozzy.apiback.model.BotApiInfo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BotApiHealthCheckRepository extends JpaRepository<BotApiHealthCheck, String> {

    List<BotApiHealthCheck> findByBotApiInfoAndDateTimeGreaterThanEqual(BotApiInfo botApiInfo, LocalDateTime dateTime);

}
