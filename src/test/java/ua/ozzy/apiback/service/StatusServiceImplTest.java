package ua.ozzy.apiback.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.ozzy.apiback.exception.ValidationException;
import ua.ozzy.apiback.model.Status;
import ua.ozzy.apiback.repository.StatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {

    @Mock
    private StatusRepository mockStatusRepository;

    @InjectMocks
    private StatusServiceImpl statusService;

    @Test
    void createStatusTest() {
        Status status = getDefaultTestStatus();
        statusService.createStatus(status);
        verify(mockStatusRepository).save(status);
    }

    @Test
    void createStatusNonUniqueNameTest() {
        Status status = getDefaultTestStatus();
        String expectedExceptionMsg = "Status with name '" + status.getName() + "' already exists";
        mockExistsStatusWithNameToTrue(status.getName());
        ValidationException thrownException = assertThrows(ValidationException.class,
                () -> statusService.createStatus(status));
        assertEquals(expectedExceptionMsg, thrownException.getMessage());
    }

    private void mockExistsStatusWithNameToTrue(String name) {
        Optional<Status> optStatus = Optional.of(new Status());
        when(mockStatusRepository.findByName(name))
                .thenReturn(optStatus);
    }

    @Test
    void createDefaultStatusTest() {
        Status status = getDefaultTestStatus();
        status.setDefault(true);
        List<Status> testStatuses = getDefaultTestStatuses();
        when(mockStatusRepository.findByIsDefaultTrue()).thenReturn(testStatuses);
        statusService.createStatus(status);
        verifyUnsetDefaultForEach(testStatuses);
    }

    private List<Status> getDefaultTestStatuses() {
        return Stream.of("test_name_0", "test_name_1")
                .map(this::asDefaultStatusWithName)
                .collect(toList());
    }

    private void verifyUnsetDefaultForEach(List<Status> statuses) {
        int timesSaveInvoked = statuses.size() + 1; // +1 for actual status creation
        ArgumentCaptor<Status> savedStatusCaptor = ArgumentCaptor.forClass(Status.class);
        verify(mockStatusRepository, times(timesSaveInvoked)).save(savedStatusCaptor.capture());
        List<Status> savedStatuses = savedStatusCaptor.getAllValues().stream()
                .filter(statuses::contains)
                .collect(toList());
        assertEquals(statuses.size(), savedStatuses.size());
        savedStatuses.forEach(savedStatus -> assertFalse(savedStatus.getDefault()));
    }

    @Test
    void createNonDefaultStatusTest() {
        Status status = getDefaultTestStatus();
        status.setDefault(false);
        statusService.createStatus(status);
        verify(mockStatusRepository, times(0)).findByIsDefaultTrue();
    }

    private Status getDefaultTestStatus() {
        return asDefaultStatusWithName("test_name");
    }

    private Status asDefaultStatusWithName(String name) {
        Status status = new Status();
        status.setName(name);
        status.setDefault(true);
        return status;
    }

}
