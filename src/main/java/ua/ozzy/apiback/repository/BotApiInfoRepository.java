package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.BotApiInfo;

import java.util.Optional;

@Repository
public interface BotApiInfoRepository extends JpaRepository<BotApiInfo, String> {

    @Query("select apiInfo from BotApiInfo apiInfo join apiInfo.accessKeys keys where keys.keyHash = :accessKeyHash")
    Optional<BotApiInfo> findByAccessKeyHash(String accessKeyHash);

}
