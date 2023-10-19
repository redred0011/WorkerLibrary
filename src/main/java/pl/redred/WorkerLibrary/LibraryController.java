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

<<<<<<< HEAD
=======
    @PostMapping("")
>>>>>>> 872b4e4 (next commit added method "add")
    public int add(@RequestBody List<Worker>workers){
        return workerRepository.save(workers);
    }
    @PutMapping("/{id}")
    public int update(@PathVariable("id")int id, @RequestBody Worker updateWorker){
        Worker worker = workerRepository.getById(id);
        if(worker != null){
            worker.setFirstName(updateWorker.getFirstName());
            worker.setLastName(updateWorker.getLastName());
            worker.setPosition(updateWorker.getPosition());
            worker.setSalary(worker.getSalary());
        }else{
            return -1;
        }
        return 1;
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id")int id, @RequestBody Worker updatedWorker){
        Worker worker = workerRepository.getById(id);
        if(worker != null){
            if(updatedWorker.getFirstName() != null) worker.setFirstName(updatedWorker.getFirstName());
            if(updatedWorker.getLastName() != null)worker.setLastName(updatedWorker.getLastName());
            if(updatedWorker.getPosition() != null)worker.setPosition(updatedWorker.getPosition());
            if(updatedWorker.getSalary() > 0 )worker.setSalary(updatedWorker.getSalary());
            workerRepository.update(worker);
            return 1;

        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete (@PathVariable("id")int id ){
        return workerRepository.delete(id);
    }
}
