package ua.ozzy.apiback.service;

import org.springframework.stereotype.Service;
import ua.ozzy.apiback.dto.FindStatusDto;
import ua.ozzy.apiback.exception.ApiBackException;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.StatusRepository;
import ua.ozzy.apiback.util.DbUtil;

import java.util.List;

import static ua.ozzy.apiback.util.ValidationUtil.validate;
import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void createStatus(Status status) {
        validate(status, "Status must not be null");
        validateNameIsUnique(status.getName());
        status.setId(DbUtil.generateId());
        unsetDefaultStatusesIfNeeded(status);
        statusRepository.save(status);
    }

    @Override
    public void updateStatus(Status status) {
        validate(status, "Status must not be null");
        validateNotNull(status.getId(), "Missing status id");
        if (hasChangedName(status)) {
            validateNameIsUnique(status.getName());
        }
        unsetDefaultStatusesIfNeeded(status);
        statusRepository.save(status);
    }

    private boolean hasChangedName(Status status) {
        Status originalStatus = getStatusById(status.getId());
        return !haveSameName(originalStatus, status);
    }

    @Override
    public Status getStatusById(String id) {
        validateNotNull(id, "Status id must not be null");
        return statusRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Status with id '" + id + "' doesn't exist"));
    }

    private boolean haveSameName(Status s0, Status s1) {
        return s0.getName().equals(s1.getName());
    }

    private void validateNameIsUnique(String name) {
        if (existsStatusWithName(name)) {
            throw new ValidationException("Status with name '" + name + "' already exists");
        }
    }

    private boolean existsStatusWithName(String name) {
        return statusRepository.findByName(name).isPresent();
    }

    private void unsetDefaultStatusesIfNeeded(Status status) {
        if (status.getDefault()) {
            unsetDefaultStatuses();
        }
    }

    private void unsetDefaultStatuses() {
        statusRepository.findByIsDefaultTrue()
                .forEach(defaultStatus -> {
                    defaultStatus.setDefault(false);
                    statusRepository.save(defaultStatus);
                });
    }

    @Override
    public void deleteStatus(Status status) {
        validateNotNull(status, "Can not delete a null status");
        validateIsNotDefault(status);
        validateIsNotInUse(status);
        statusRepository.delete(status);
    }

    private void validateIsNotDefault(Status status) {
        if (status.getDefault()) {
            throw new ValidationException("Can not delete a default status");
        }
    }

    private void validateIsNotInUse(Status status) {
        if (isStatusInUse(status)) {
            throw new ValidationException("Can not delete a status. The status has active usages");
        }
    }

    private boolean isStatusInUse(Status status) {
        return statusRepository.countUsagesById(status.getId()) > 0;
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
    public List<Status> findStatuses(FindStatusDto findStatusDto) {
        validateNotNull(findStatusDto, "Status search criteria is null");
        return statusRepository.findBy(findStatusDto);
    }

}
