package pl.redred.WorkerLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Worker>getAll(){
        return jdbcTemplate.query("SELECT id, firstName, lastName, position, salary FROM worker",
                BeanPropertyRowMapper.newInstance(Worker.class));

    }
    public Worker getById(int id){
         return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, position, salary FROM worker WHERE id = ?",
                 BeanPropertyRowMapper.newInstance(Worker.class), id);

    }

    public int save(List<Worker> workers) {
        workers.forEach(worker -> jdbcTemplate
                .update("INSERT INTO worker(firstName,lastName,position,salary) VALUES (?, ?, ?, ?)",
                        worker.getFirstName(),worker.getLastName(),worker.getPosition(),worker.getSalary()));
        return 1;
    }
    public int update(Worker worker){
        return jdbcTemplate.update("UPDATE worker SET firstName = ?, lastName = ?, position = ?, salary = ? WHERE id = ?",
                worker.getFirstName(), worker.getLastName(), worker.getPosition(), worker.getSalary(), worker.getId());
    }

    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM worker WHERE id = ?",id );
    }


}
