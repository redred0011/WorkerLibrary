package pl.redred.WorkerLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library") // Adnotacja określa ścieżkę bazową dla wszystkich metod w tym kontrolerze.
public class LibraryController {

    @Autowired // Wstrzykiwanie zależności dla WorkerRepository.
    WorkerRepository workerRepository;

    // Metoda zwracająca listę wszystkich pracowników.
    @GetMapping("")
    public List<Worker> getAll(){
        return workerRepository.getAll();
    }

    // Metoda zwracająca pracownika na podstawie podanego id.
    @GetMapping("/{id}")
    public Worker getById(@PathVariable("id")int id){
        return workerRepository.getById(id);
    }

    // Metoda dodająca listę pracowników do bazy danych.
    @PostMapping("")
    public int add(@RequestBody List<Worker>workers){
        return workerRepository.save(workers);
    }

    // Metoda aktualizująca dane pracownika w bazie danych.
    @PutMapping("/{id}")
    public int update(@PathVariable("id")int id, @RequestBody Worker updateWorker){
        Worker worker = workerRepository.getById(id);
        if(worker != null){
            worker.setFirstName(updateWorker.getFirstName());
            worker.setLastName(updateWorker.getLastName());
            worker.setPosition(updateWorker.getPosition());
            worker.setSalary(worker.getSalary());
            workerRepository.update(worker);
        }else{
            return -1;
        }
        return 1;
    }
    // Metoda częściowo aktualizująca dane pracownika w bazie danych.
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

    // Metoda usuwająca pracownika na podstawie podanego id.
    @DeleteMapping("/{id}")
    public int delete (@PathVariable("id")int id ){
        return workerRepository.delete(id);
    }

    //Metoda zwracająca pracowników którzy zarabiają wyższą pensję niż podana pensja
    @PostMapping("/salary/{salary}")
    public List<Worker> searchSalary(@PathVariable("salary") double salary){
        return workerRepository.searchSalary(salary);
    }

    //Metoda zwracająca pracowników o podanym stanowisku
    @PostMapping("/position/{position}")
    public List<Worker> searchPosition(@PathVariable("position") String position){
        return workerRepository.searchPosition(position);
    }
}
