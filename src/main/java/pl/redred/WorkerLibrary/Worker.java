package pl.redred.WorkerLibrary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int Id;
    private String firstName;
    private String lastName;
    private String position;
    private double salary;
}
