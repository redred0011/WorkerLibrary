package org.example.WorkerLibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.WorkerLibrary.Model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.Model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.Model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.Model.dto.WorkerDto;
import org.example.WorkerLibrary.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/libraries")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping()
    public List<WorkerDto> getAll() {
        return workerService.getAll();
    }

    @GetMapping("/{id}")
    public WorkerDto getById(@PathVariable("id") int id) {
        return workerService.getById(id);
    }

    @PostMapping()
    public void add(@Valid @RequestBody List<CreateWorkerCommand> createWorkerCommands) {
        workerService.add(createWorkerCommands);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @Valid @RequestBody UpdateWorkerCommand updateWorkerCommand) {
        workerService.update(id, updateWorkerCommand);
    }

    @PatchMapping("/{id}")
    public void partiallyUpdate(@PathVariable("id") int id, @Valid @RequestBody PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand) {
        workerService.partiallyUpdate(id, partiallyUpdateWorkerCommand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        workerService.delete(id);
    }

    @PostMapping("/salary/{salary}")
    public List<WorkerDto> searchSalary(@PathVariable("salary") double salary) {
        return workerService.searchSalary(salary);
    }

    @PostMapping("/position/{position}")
    public List<WorkerDto> searchPosition(@PathVariable("position") String position) {
        return workerService.searchPosition(position);
    }
}
