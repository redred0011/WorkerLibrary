package org.example.WorkerLibrary.service;

import lombok.RequiredArgsConstructor;
import org.example.WorkerLibrary.Model.Worker;
import org.example.WorkerLibrary.Model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.Model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.Model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.Model.dto.WorkerDto;
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
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));
        return workerMapper.toDto(worker);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void add(List<CreateWorkerCommand> createWorkerCommands) {
        List<Worker> workers = createWorkerCommands.stream()
                .map(workerMapper::toEntity)
                .collect(Collectors.toList());
        workerRepository.saveAll(workers);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void update(int id, UpdateWorkerCommand updateWorkerCommand) {
        try {
            Worker worker = workerRepository.findById(id)
                    .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));

            workerMapper.updateEntity(updateWorkerCommand, worker);
            workerRepository.save(worker);
        } catch (DataAccessException e) {
            throw new WorkerUpdateException("Error updating worker with id " + id);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void partiallyUpdate(int id, PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand) {
        try {
            Worker worker = workerRepository.findById(id)
                    .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));

            workerMapper.partiallyUpdateEntity(partiallyUpdateWorkerCommand, worker);
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
