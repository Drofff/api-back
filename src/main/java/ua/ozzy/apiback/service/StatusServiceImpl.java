package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.StatusRepository;

import java.util.List;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status getDefaultStatus() {
        return statusRepository.findFirstByIsDefaultTrue()
                .orElseThrow(() -> new ApiBackException("No default feedback status defined"));
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(String id) {
        validateNotNull(id, "Status id must not be null");
        return statusRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Status with id '" + id + "' doesn't exist"));
    }

}
