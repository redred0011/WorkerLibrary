package pl.redred.WorkerLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerRepository {
    // Wstrzykiwanie zależności dla JdbcTemplate.
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Metoda zwracająca listę wszystkich pracowników.
    public List<Worker>getAll(){
        return jdbcTemplate.query("SELECT id, firstName, lastName, position, salary FROM worker",
                BeanPropertyRowMapper.newInstance(Worker.class));

    }
    // Metoda zwracająca pracownika na podstawie podanego id.
    public Worker getById(int id){
         return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, position, salary FROM worker WHERE id = ?",
                 BeanPropertyRowMapper.newInstance(Worker.class), id);

    }

    // Metoda zapisująca listę pracowników do bazy danych.
    public int save(List<Worker> workers) {
        workers.forEach(worker -> jdbcTemplate
                .update("INSERT INTO worker(firstName,lastName,position,salary) VALUES (?, ?, ?, ?)",
                        worker.getFirstName(),worker.getLastName(),worker.getPosition(),worker.getSalary()));
        return 1;
    }
    // Metoda aktualizująca dane pracownika w bazie danych.
    public int update(Worker worker){
        return jdbcTemplate.update("UPDATE worker SET firstName = ?, lastName = ?, position = ?, salary = ? WHERE id = ?",
                worker.getFirstName(), worker.getLastName(), worker.getPosition(), worker.getSalary(), worker.getId());
    }

    // Metoda usuwająca pracownika na podstawie podanego id.
    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM worker WHERE id = ?",id );
    }

    //Metoda zwracająca pracowników którzy zarabiają wyższą pensję niż podana
    public List<Worker> searchSalary(double searchSalary){
        return jdbcTemplate.query("SELECT id, firstName, lastName, position, salary FROM worker WHERE salary > ?",
                BeanPropertyRowMapper.newInstance(Worker.class), searchSalary);
    }
    //Metoda zwracająca listę pracowników o podanym przez użytkownika stanowisku
    public List<Worker> searchPosition(String position){
        return jdbcTemplate.query("SELECT id, firstName, lastName, position, salary FROM worker WHERE position = ?",
                BeanPropertyRowMapper.newInstance(Worker.class), position);
    }
}
