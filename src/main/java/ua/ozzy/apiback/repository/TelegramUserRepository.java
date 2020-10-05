package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.TelegramUser;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {
}
