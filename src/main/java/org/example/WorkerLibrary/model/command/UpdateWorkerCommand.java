package org.example.WorkerLibrary.model.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkerCommand {
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Position is mandatory")
    private String position;

    @NotNull(message = "Salary is mandatory")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;
}
