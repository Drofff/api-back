package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.BotApiInfo;

import java.util.Optional;

@Repository
public interface BotApiInfoRepository extends JpaRepository<BotApiInfo, String> {

    Optional<BotApiInfo> findByAccessKeyHash(String accessKeyHash);

}
