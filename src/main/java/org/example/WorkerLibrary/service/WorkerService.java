package org.example.WorkerLibrary.service;

import lombok.RequiredArgsConstructor;
import org.example.WorkerLibrary.model.Worker;
import org.example.WorkerLibrary.model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.model.dto.WorkerDto;
import org.example.WorkerLibrary.exception.WorkerNotFoundException;
import org.example.WorkerLibrary.exception.WorkerPartiallyUpdateException;
import org.example.WorkerLibrary.exception.WorkerUpdateException;
import org.example.WorkerLibrary.mapper.WorkerMapper;
import org.example.WorkerLibrary.repository.WorkerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<WorkerDto> getAll() {
        return workerRepository.findAll().stream()
                .map(workerMapper::toDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WorkerDto getById(int id) {
        return workerMapper.toDto(workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found")));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void add(List<CreateWorkerCommand> createWorkerCommands) {
        List<Worker> workers = createWorkerCommands.stream()
                .map(workerMapper::toEntity)
                .collect(Collectors.toList());
        try {
            workerRepository.saveAll(workers);
        } catch (DataAccessException e) {
            throw new WorkerUpdateException("Error save");
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void update(int id, UpdateWorkerCommand updateWorkerCommand) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));
        workerMapper.updateEntity(updateWorkerCommand, worker);
        try {
            workerRepository.save(worker);
        } catch (DataAccessException e) {
            throw new WorkerUpdateException("Error updating worker with id " + id);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void partiallyUpdate(int id, PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));
        workerMapper.partiallyUpdateEntity(partiallyUpdateWorkerCommand, worker);

        try {
            workerRepository.save(worker);
        } catch (DataAccessException e) {
            throw new WorkerPartiallyUpdateException("Error partially updating worker with id " + id);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(int id) {
        if (!workerRepository.existsById(id)) {
            throw new WorkerNotFoundException("Worker with id " + id + " not found");
        }
        workerRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<WorkerDto> searchSalary(double salary) {
        return workerRepository.findBySalaryGreaterThan(salary).stream()
                .map(workerMapper::toDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<WorkerDto> searchPosition(String position) {
        return workerRepository.findByPosition(position).stream()
                .map(workerMapper::toDto)
                .collect(Collectors.toList());
    }
}
