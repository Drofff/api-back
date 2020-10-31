package ua.ozzy.apiback.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ozzy.apiback.dto.FindStatusDto;
import ua.ozzy.apiback.dto.FindStatusTestDto;
import ua.ozzy.apiback.model.Status;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SimpleEntitySearchTest {

    @Autowired
    private StatusRepository statusRepository;

    @BeforeEach
    void insertStatuses() {
        List<Status> statuses = getTestStatuses();
        statusRepository.saveAll(statuses);
    }

    @AfterEach
    void cleanupStatuses() {
        List<String> statusIds = getTestStatusIds();
        statusIds.forEach(statusRepository::deleteById);
    }

    private List<String> getTestStatusIds() {
        return getTestStatuses().stream()
                .map(Status::getId)
                .collect(toList());
    }

    private List<Status> getTestStatuses() {
        Status s0 = new Status("test_id_0", "Not done", false);
        Status s1 = new Status("test_id_1", "Done", true);
        Status s2 = new Status("test_id_2", "In progress", false);
        Status s3 = new Status("test_id_3", "In progress", false);
        return List.of(s0, s1, s2, s3);
    }

    @Test
    void findByNameTest() {
        String expectedStatusName = "Done";
        FindStatusDto searchCriteria = statusWithNameCriteria(expectedStatusName);
        List<Status> searchResult = statusRepository.findBy(searchCriteria);
        assertEquals(1, searchResult.size());
        Status resultStatus = searchResult.get(0);
        assertEquals(expectedStatusName, resultStatus.getName());
    }

    private FindStatusDto statusWithNameCriteria(String name) {
        return new FindStatusDto(name);
    }

    @Test
    void findByNameAndIdTest() {
        String expectedStatusId = "test_id_3";
        String expectedStatusName = "In progress";
        FindStatusTestDto searchCriteria = new FindStatusTestDto(expectedStatusId, expectedStatusName);
        List<Status> searchResult = statusRepository.findBy(searchCriteria);
        assertEquals(1, searchResult.size());
        Status resultStatus = searchResult.get(0);
        assertEquals(expectedStatusId, resultStatus.getId());
        assertEquals(expectedStatusName, resultStatus.getName());
    }

}
