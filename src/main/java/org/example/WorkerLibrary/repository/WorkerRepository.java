package org.example.WorkerLibrary.repository;

import org.example.WorkerLibrary.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    List<Worker> findBySalaryGreaterThan(double salary);

    List<Worker> findByPosition(String position);
}

