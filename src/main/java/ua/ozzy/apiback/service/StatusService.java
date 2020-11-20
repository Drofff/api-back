package ua.ozzy.apiback.service;

import ua.ozzy.apiback.dto.FindStatusDto;
import ua.ozzy.apiback.model.Status;

import java.util.List;

public interface StatusService {

    void createStatus(Status status);

    void updateStatus(Status status);

    Status getStatusById(String id);

    void deleteStatus(Status status);

    Status getDefaultStatus();

    List<Status> getStatuses();

    List<Status> findStatuses(FindStatusDto findStatusDto);

}
