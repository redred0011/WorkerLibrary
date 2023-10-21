package pl.redred.WorkerLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Użyj rzeczywistej bazy danych do testów
public class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    @BeforeEach
    public void setUp() {
        // Tutaj możesz wstawić kod do wstawiania przykładowych danych testowych do bazy danych.
    }

    @Test
    public void testGetAll() {
        List<Worker> workers = workerRepository.getAll();
        assertNotNull(workers);
        assertTrue(workers.size() > 0);
    }

    @Test
    public void testGetById() {
        int id = 1; // Zastąp to odpowiednim istniejącym ID pracownika w bazie danych
        Worker worker = workerRepository.getById(id);
        assertNotNull(worker);
        assertEquals(id, worker.getId());
    }

    @Test
    public void testSave() {
        // Tworzenie nowego pracownika
        Worker newWorker = new Worker("John", "Doe", "Manager", 60000.0);

        int result = workerRepository.save(List.of(newWorker));
        assertEquals(1, result); // Zakładamy, że zapis powinien zakończyć się sukcesem
    }

    @Test
    public void testUpdate() {
        int id = 1; // Zastąp to odpowiednim istniejącym ID pracownika w bazie danych
        Worker worker = workerRepository.getById(id);
        assertNotNull(worker);

        // Zaktualizuj dane pracownika
        worker.setFirstName("UpdatedFirstName");
        worker.setLastName("UpdatedLastName");
        worker.setPosition("UpdatedPosition");
        worker.setSalary(70000.0);

        int result = workerRepository.update(worker);
        assertEquals(1, result); // Zakładamy, że aktualizacja powinna zakończyć się sukcesem

        Worker updatedWorker = workerRepository.getById(id);
        assertEquals("UpdatedFirstName", updatedWorker.getFirstName());
        assertEquals("UpdatedLastName", updatedWorker.getLastName());
        assertEquals("UpdatedPosition", updatedWorker.getPosition());
        assertEquals(70000.0, updatedWorker.getSalary(), 0.01);
    }

    @Test
    public void testDelete() {
        int id = 1; // Zastąp to odpowiednim istniejącym ID pracownika w bazie danych
        int result = workerRepository.delete(id);
        assertEquals(1, result); // Zakładamy, że usunięcie powinno zakończyć się sukcesem
    }

    @Test
    public void testSearchSalary() {
        double searchSalary = 60000.0; // Zastąp to odpowiednią wartością pensji
        List<Worker> workers = workerRepository.searchSalary(searchSalary);
        assertNotNull(workers);

        for (Worker worker : workers) {
            assertTrue(worker.getSalary() > searchSalary);
        }
    }

    @Test
    public void testSearchPosition() {
        String position = "Manager"; // Zastąp to odpowiednią stanowiskiem
        List<Worker> workers = workerRepository.searchPosition(position);
        assertNotNull(workers);

        for (Worker worker : workers) {
            assertEquals(position, worker.getPosition());
        }
    }
}
