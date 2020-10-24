package ua.ozzy.apiback.service;

import ua.ozzy.apiback.model.Status;

import java.util.List;

public interface StatusService {

    Status getDefaultStatus();

    List<Status> getStatuses();

    Status getStatusById(String id);

}
