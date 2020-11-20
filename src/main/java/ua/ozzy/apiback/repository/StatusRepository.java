package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, String>, StatusSearch {

    Optional<Status> findFirstByIsDefaultTrue();

    List<Status> findByIsDefaultTrue();

    Optional<Status> findByName(String name);

    @Query("select count(f) from Feedback f where f.status.id = :statusId")
    long countUsagesById(String statusId);

}
