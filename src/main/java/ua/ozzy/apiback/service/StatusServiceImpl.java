package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.StatusRepository;

import java.util.List;

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

}
