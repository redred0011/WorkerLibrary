package org.example.WorkerLibrary.service;

import org.example.WorkerLibrary.Model.Worker;
import org.example.WorkerLibrary.Model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.Model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.Model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.Model.dto.WorkerDto;
import org.example.WorkerLibrary.exception.WorkerNotFoundException;
import org.example.WorkerLibrary.mapper.WorkerMapper;
import org.example.WorkerLibrary.repository.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkerServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @Mock
    private WorkerMapper workerMapper;

    @InjectMocks
    private WorkerService workerService;

    @Test
    public void testGetAll_HappyPath_ReturnedListWorkers() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        WorkerDto workerDto = WorkerDto.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        when(workerRepository.findAll()).thenReturn(Arrays.asList(worker));
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        List<WorkerDto> result = workerService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Bartek");
    }

    @Test
    public void testGetAll_ReturnedEmptyList() {
        when(workerRepository.findAll()).thenReturn(Arrays.asList());

        List<WorkerDto> result = workerService.getAll();

        assertThat(result).isEmpty();
    }

    @Test
    public void testGetById_HappyPath_ReturnedWorkerById() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        WorkerDto workerDto = WorkerDto.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        when(workerRepository.findById(1)).thenReturn(Optional.of(worker));
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        WorkerDto result = workerService.getById(1);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Bartek");
    }

    @Test
    public void testGetById_ReturnedNotFound() {
        when(workerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(WorkerNotFoundException.class, () -> workerService.getById(1));
    }

    @Test
    public void testAdd_HappyPath_ReturnedAddedNewWorker() {
        CreateWorkerCommand createWorkerCommand = CreateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1500.00)
                .build();

        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1500.00)
                .build();

        when(workerMapper.toEntity(createWorkerCommand)).thenReturn(worker);

        workerService.add(Arrays.asList(createWorkerCommand));

        verify(workerRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testAdd_ReturnedDataAccessException() {
        CreateWorkerCommand createWorkerCommand = CreateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1500.00)
                .build();

        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1500.00)
                .build();

        when(workerMapper.toEntity(createWorkerCommand)).thenReturn(worker);
        doThrow(new DataAccessException("...") {
        }).when(workerRepository).saveAll(anyList());

        assertThrows(DataAccessException.class, () -> workerService.add(Arrays.asList(createWorkerCommand)));
    }

    @Test
    public void testUpdate_HappyPath_ReturnedUpdateWorker() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        UpdateWorkerCommand updateWorkerCommand = UpdateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1600.00)
                .build();

        when(workerRepository.findById(1)).thenReturn(Optional.of(worker));

        workerService.update(1, updateWorkerCommand);

        verify(workerMapper, times(1)).updateEntity(updateWorkerCommand, worker);
        verify(workerRepository, times(1)).save(worker);
    }

    @Test
    public void testUpdate_ReturnedNotFound() {
        UpdateWorkerCommand updateWorkerCommand = UpdateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1600.00)
                .build();

        when(workerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(WorkerNotFoundException.class, () -> workerService.update(1, updateWorkerCommand));
    }

    @Test
    public void testPartiallyUpdate_HappyPath_ReturnedPartiallyUpdateWorker() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand = PartiallyUpdateWorkerCommand.builder()
                .lastName("Partially Updated")
                .build();

        when(workerRepository.findById(1)).thenReturn(Optional.of(worker));

        workerService.partiallyUpdate(1, partiallyUpdateWorkerCommand);

        verify(workerMapper, times(1)).partiallyUpdateEntity(partiallyUpdateWorkerCommand, worker);
        verify(workerRepository, times(1)).save(worker);
    }

    @Test
    public void testPartiallyUpdate_ReturnedNotFound() {
        PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand = PartiallyUpdateWorkerCommand.builder()
                .lastName("Partially Updated")
                .build();

        when(workerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(WorkerNotFoundException.class, () -> workerService.partiallyUpdate(1, partiallyUpdateWorkerCommand));
    }

    @Test
    public void testDelete_HappyPath_ReturnedDeleteWorker() {
        when(workerRepository.existsById(1)).thenReturn(true);

        workerService.delete(1);

        verify(workerRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDelete_ReturnedNotFound() {
        when(workerRepository.existsById(1)).thenReturn(false);

        assertThrows(WorkerNotFoundException.class, () -> workerService.delete(1));
    }

    @Test
    public void testSearchSalary_HappyPath_SearchedSalary() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        WorkerDto workerDto = WorkerDto.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        when(workerRepository.findBySalaryGreaterThan(1500.00)).thenReturn(Arrays.asList(worker));
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        List<WorkerDto> result = workerService.searchSalary(1500.00);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Bartek");
    }

    @Test
    public void testSearchSalary_ReturnedNoResults() {
        when(workerRepository.findBySalaryGreaterThan(1500.00)).thenReturn(Arrays.asList());

        List<WorkerDto> result = workerService.searchSalary(1500.00);

        assertThat(result).isEmpty();
    }

    @Test
    public void testSearchPosition_HappyPath_ReturnedSearchedPosition() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        WorkerDto workerDto = WorkerDto.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        when(workerRepository.findByPosition("Java Developer")).thenReturn(Arrays.asList(worker));
        when(workerMapper.toDto(worker)).thenReturn(workerDto);

        List<WorkerDto> result = workerService.searchPosition("Java Developer");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Bartek");
    }

    @Test
    public void testSearchPosition_ReturnedNoResults() {
        when(workerRepository.findByPosition("Java Developer")).thenReturn(Arrays.asList());

        List<WorkerDto> result = workerService.searchPosition("Java Developer");

        assertThat(result).isEmpty();
    }
}
