package ua.ozzy.apiback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.Feedback;
import ua.ozzy.apiback.model.TelegramUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

    Optional<Feedback> findFirstByOrderByDateTimeDesc();

    Page<Feedback> findByOrderByDateTimeDesc(Pageable pageable);

    List<Feedback> findByAssignedUser(TelegramUser user);

}
