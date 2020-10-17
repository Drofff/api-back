package ua.ozzy.apiback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.model.AccessKey;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, String> {
}
