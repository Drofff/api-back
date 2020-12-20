package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramMessage;

import java.util.Optional;

@Repository
public interface TelegramMessageRepository extends JpaRepository<TelegramMessage, String> {

    Optional<TelegramMessage> findByFeedback(Feedback feedback);

}
