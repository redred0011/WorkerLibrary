package pl.redred.WorkerLibrary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibraryControllerTest {

    @Mock
    private WorkerRepository workerRepository;

    private LibraryController libraryController;

    @BeforeEach
    public void przygotowanie() {
        // Inicjalizacja obiektów mock dla testów przy użyciu MockitoAnnotations
        MockitoAnnotations.openMocks(this);

        // Tworzenie instancji kontrolera i wstrzyknięcie zależności do symulowanego repozytorium
        libraryController = new LibraryController();
        libraryController.workerRepository = workerRepository;
    }

    @Test
    public void testPobieraniaWszystkichPracownikow() {
        // Tworzenie danych symulowanych dla metody getAll()
        List<Worker> pracownicy = new ArrayList<>();
        pracownicy.add(new Worker(1, "John", "Doe", "Manager", 50000.0));
        pracownicy.add(new Worker(2, "Jane", "Smith", "Engineer", 60000.0));

        // Konfiguracja obiektu mock do zwracania danych symulowanych
        Mockito.when(workerRepository.getAll()).thenReturn(pracownicy);

        // Wywołanie metody kontrolera, którą testujemy
        List<Worker> wynik = libraryController.getAll();

        // Sprawdzenie, czy wynik jest zgodny z oczekiwanymi danymi symulowanymi
        assertEquals(pracownicy.size(), wynik.size());

        // Sprawdzenie właściwości poszczególnych pracowników
        for (int i = 0; i < pracownicy.size(); i++) {
            Worker oczekiwanyPracownik = pracownicy.get(i);
            Worker rzeczywistyPracownik = wynik.get(i);

            assertEquals(oczekiwanyPracownik.getId(), rzeczywistyPracownik.getId());
            assertEquals(oczekiwanyPracownik.getFirstName(), rzeczywistyPracownik.getFirstName());
            assertEquals(oczekiwanyPracownik.getLastName(), rzeczywistyPracownik.getLastName());
            assertEquals(oczekiwanyPracownik.getPosition(), rzeczywistyPracownik.getPosition());
            assertEquals(oczekiwanyPracownik.getSalary(), rzeczywistyPracownik.getSalary(), 0.01);

        }
    }
}
