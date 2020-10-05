package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.TelegramGroup;

@Repository
public interface TelegramGroupRepository extends JpaRepository<TelegramGroup, String> {
}
