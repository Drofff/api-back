package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.Status;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, String>, StatusSearch {

    Optional<Status> findFirstByIsDefaultTrue();

}
