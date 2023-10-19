package pl.redred.WorkerLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    WorkerRepository workerRepository;

    @GetMapping("")
    public List<Worker> getAll(){
        return workerRepository.getAll();
    }

    @GetMapping("/{id}")
    public Worker getById(@PathVariable("id")int id){
        return workerRepository.getById(id);
    }

    public int add(@RequestBody List<Worker>workers){
        return workerRepository.save(workers);
    }
}
