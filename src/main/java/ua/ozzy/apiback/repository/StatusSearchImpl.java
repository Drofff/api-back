package ua.ozzy.apiback.repository;

import org.springframework.stereotype.Repository;
import ua.ozzy.apiback.dto.FindStatusDto;
import ua.ozzy.apiback.model.Status;

import javax.persistence.EntityManager;

@Repository
public class StatusSearchImpl extends SimpleEntitySearch<Status, FindStatusDto> implements StatusSearch {

    public StatusSearchImpl(EntityManager entityManager) {
        super(entityManager);
    }

}
