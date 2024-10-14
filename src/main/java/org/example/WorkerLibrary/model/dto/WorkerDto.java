package org.example.WorkerLibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private double salary;
}
