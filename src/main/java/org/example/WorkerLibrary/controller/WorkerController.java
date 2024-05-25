package org.example.WorkerLibrary.controller;

import lombok.RequiredArgsConstructor;
import org.example.WorkerLibrary.Model.Worker;
import org.example.WorkerLibrary.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/libraries")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping()
    public List<Worker> getAll() {
        return workerService.getAll();
    }

    @GetMapping("/{id}")
    public Worker getById(@PathVariable("id") int id) {
        return workerService.getById(id);
    }

    @PostMapping()
    public void add(@RequestBody List<Worker> workers) {
        workerService.add(workers);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Worker updateWorker) {
        workerService.update(id, updateWorker);
    }

    @PatchMapping("/{id}")
    public void partiallyUpdate(@PathVariable("id") int id, @RequestBody Worker updatedWorker) {
        workerService.partiallyUpdate(id, updatedWorker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        workerService.delete(id);
    }

    @PostMapping("/salary/{salary}")
    public List<Worker> searchSalary(@PathVariable("salary") double salary) {
        return workerService.searchSalary(salary);
    }

    @PostMapping("/position/{position}")
    public List<Worker> searchPosition(@PathVariable("position") String position) {
        return workerService.searchPosition(position);
    }
}
