package org.example.WorkerLibrary.Model.dto;

import lombok.Data;

@Data
public class WorkerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private double salary;
}
