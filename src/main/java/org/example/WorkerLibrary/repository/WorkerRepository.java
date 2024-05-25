package org.example.WorkerLibrary.repository;

import jakarta.persistence.LockModeType;
import org.example.WorkerLibrary.Model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    List<Worker> findBySalaryGreaterThan(double salary);

    List<Worker> findByPosition(String position);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Worker>findWithLockingById(int id);
}

