# WorkerLibrary

WorkerLibrary to aplikacja Java - REST API, SPRING BOOT, DOCKER, MySQL, JDBC która zarządza pracownikami w bazie danych. 
Pozwala na pobieranie, dodawanie, aktualizowanie i usuwanie pracowników oraz wyszukiwanie pracowników na podstawie różnych kryteriów.

## Klasa WorkerRepository

`WorkerRepository` to klasa odpowiedzialna za dostęp do danych pracowników w bazie danych. Udostępnia następujące funkcje:

### `getAll()`

Metoda zwracająca listę wszystkich pracowników w bazie danych.

### `getById(int id)`

Metoda zwracająca pracownika na podstawie podanego ID.

### `save(List<Worker> workers)`

Metoda zapisująca listę pracowników do bazy danych.

### `update(Worker worker)`

Metoda aktualizująca dane pracownika w bazie danych.

### `delete(int id)`

Metoda usuwająca pracownika na podstawie podanego ID.

### `searchSalary(double searchSalary)`

Metoda zwracająca pracowników, którzy zarabiają wyższą pensję niż podana pensja.

### `searchPosition(String position)`

Metoda zwracająca pracowników o podanym stanowisku.

## Klasa Worker

`Worker` to klasa reprezentująca pracownika. Zawiera pola takie jak ID, imię, nazwisko, stanowisko i pensja. Posiada konstruktory oraz metody do walidacji danych pracownika.

## Klasa LibraryController

`LibraryController` to kontroler REST API obsługujący operacje na pracownikach. Udostępnia następujące endpointy:

### `GET /library`

Zwraca listę wszystkich pracowników.

### `GET /library/{id}`

Zwraca pracownika na podstawie ID.

### `POST /library`

Dodaje listę pracowników do bazy danych.

### `PUT /library/{id}`

Aktualizuje dane pracownika na podstawie ID.

### `PATCH /library/{id}`

Częściowo aktualizuje dane pracownika na podstawie ID.

### `DELETE /library/{id}`

Usuwa pracownika na podstawie ID.

### `POST /library/salary/{salary}`

Wyszukuje pracowników, którzy zarabiają więcej niż podana pensja.

### `POST /library/position/{position}`

Wyszukuje pracowników o podanym stanowisku.

## Programy wykorzystane do tworzenia:

1. IntelliJ IDE
2. DockerDesktop
3. MySQL Workbench
4. Postman 

Dzięki tej aplikacji możesz zarządzać pracownikami w bazie danych w prosty sposób. W razie problemów lub pytań, skontaktuj się z autorem.

---

Autor: [redred0011](https://github.com/redred0011)
