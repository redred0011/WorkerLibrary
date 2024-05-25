package org.example.WorkerLibrary.service;

import lombok.RequiredArgsConstructor;
import org.example.WorkerLibrary.Model.Worker;
import org.example.WorkerLibrary.exception.WorkerNotFoundException;
import org.example.WorkerLibrary.exception.WorkerPartiallyUpdateException;
import org.example.WorkerLibrary.exception.WorkerUpdateException;
import org.example.WorkerLibrary.repository.WorkerRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    public Worker getById(int id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));
    }

    public void add(List<Worker> workers) {
        workerRepository.saveAll(workers);
    }

    public void update(int id, Worker updateWorker) {
        try {
            Worker worker = workerRepository.findWithLockingById(id)
                    .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));

            worker.setFirstName(updateWorker.getFirstName());
            worker.setLastName(updateWorker.getLastName());
            worker.setPosition(updateWorker.getPosition());
            worker.setSalary(updateWorker.getSalary());
            workerRepository.save(worker);
        } catch (DataAccessException e) {
            throw new WorkerUpdateException("Error updating worker with id " + id);
        }
    }

    public void partiallyUpdate(int id, Worker updatedWorker) {
        try {
            Worker worker = workerRepository.findWithLockingById(id)
                    .orElseThrow(() -> new WorkerNotFoundException("Worker with id " + id + " not found"));

            if (updatedWorker.getFirstName() != null) worker.setFirstName(updatedWorker.getFirstName());
            if (updatedWorker.getLastName() != null) worker.setLastName(updatedWorker.getLastName());
            if (updatedWorker.getPosition() != null) worker.setPosition(updatedWorker.getPosition());
            if (updatedWorker.getSalary() > 0) worker.setSalary(updatedWorker.getSalary());
            workerRepository.save(worker);
        } catch (DataAccessException e) {
            throw new WorkerPartiallyUpdateException("Error partially updating worker with id " + id);
        }
    }

    public void delete(int id) {
        if (!workerRepository.existsById(id)) {
            throw new WorkerNotFoundException("Worker with id " + id + " not found");
        }
        workerRepository.deleteById(id);
    }

    public List<Worker> searchSalary(double salary) {
        return workerRepository.findBySalaryGreaterThan(salary);
    }

    public List<Worker> searchPosition(String position) {
        return workerRepository.findByPosition(position);
    }
}
