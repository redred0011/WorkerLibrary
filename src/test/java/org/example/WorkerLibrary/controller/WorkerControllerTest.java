package org.example.WorkerLibrary.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.WorkerLibrary.repository.WorkerRepository;
import org.example.WorkerLibrary.Model.Worker;
import org.example.WorkerLibrary.Model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.Model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.Model.command.PartiallyUpdateWorkerCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WorkerRepository workerRepository;

    private final String MAPPING = "/api/v1/libraries";

    private final Worker worker = Worker.builder()
            .id(1)
            .firstName("Bartek")
            .lastName("Anczewski")
            .position("Programer")
            .salary(2000.00)
            .build();

    private final CreateWorkerCommand createWorkerCommand = CreateWorkerCommand.builder()
            .firstName("Bartek")
            .lastName("Anczewski")
            .position("Java Developer")
            .salary(1500.00)
            .build();

    private final UpdateWorkerCommand updateWorkerCommand = UpdateWorkerCommand.builder()
            .firstName("Bartek")
            .lastName("Anczewskii")
            .position("Java Developer")
            .salary(1600.00)
            .build();

    private final PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand = PartiallyUpdateWorkerCommand.builder()
            .firstName("Bartek")
            .lastName("Anczewski")
            .position("Java Developer")
            .salary(1500.00)
            .build();

    private final PartiallyUpdateWorkerCommand invalidPartiallyUpdateWorkerCommand = PartiallyUpdateWorkerCommand.builder()
            .lastName("")
            .build();

    private final UpdateWorkerCommand invalidUpdateWorkerCommand = UpdateWorkerCommand.builder()
            .firstName("")
            .lastName("Doe Updated")
            .position("Tester")
            .salary(1600.00)
            .build();

    @BeforeAll
    public static void initAll() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void setUp() {
        workerRepository.deleteAll();
        workerRepository.save(worker);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetAllWorkers_HappyPath_ReturnedAllWorkers() throws Exception {
        mockMvc.perform(get(MAPPING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Bartek")))
                .andExpect(jsonPath("$[0].lastName", is("Anczewski")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetWorkerById_HappyPath_ReturnedWorkerById() throws Exception {
        mockMvc.perform(get(MAPPING + "/{id}", worker.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Bartek")))
                .andExpect(jsonPath("$.lastName", is("Anczewski")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testGetWorkerById_ReturnedWorkerNotFound() throws Exception {
        mockMvc.perform(get(MAPPING + "/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAddWorker_HappyPath_ReturnedNewWorker() throws Exception {
        mockMvc.perform(post(MAPPING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(createWorkerCommand)))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testUpdateWorker_HappyPath_ReturnedUpdateData() throws Exception {
        mockMvc.perform(put(MAPPING + "/{id}", worker.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateWorkerCommand))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testUpdateWorker_ReturnedInvalidInput() throws Exception {
        mockMvc.perform(put(MAPPING + "/{id}", worker.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUpdateWorkerCommand))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testPartiallyUpdateWorker_HappyPath_ReturnedPartiallyUpdateWorker() throws Exception {
        mockMvc.perform(patch(MAPPING + "/{id}", worker.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partiallyUpdateWorkerCommand))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testPartiallyUpdateWorker_ReturnedInvalidInput() throws Exception {
        mockMvc.perform(patch(MAPPING + "/{id}", worker.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPartiallyUpdateWorkerCommand))
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testDeleteWorker_HappyPath_ReturnedDeleteWorker() throws Exception {
        mockMvc.perform(delete(MAPPING + "/{id}", worker.getId())
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testDeleteWorker_ReturnedWorkerNotFound() throws Exception {
        mockMvc.perform(delete(MAPPING + "/{id}", 9999)
                        .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUnauthorizedAccess_ReturnedUnauthorizedAccess() throws Exception {
        mockMvc.perform(get(MAPPING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
