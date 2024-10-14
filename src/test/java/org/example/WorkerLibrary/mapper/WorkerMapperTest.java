package org.example.WorkerLibrary.mapper;

import org.example.WorkerLibrary.model.Worker;
import org.example.WorkerLibrary.model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.model.dto.WorkerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkerMapperTest {

    private final WorkerMapper workerMapper = Mappers.getMapper(WorkerMapper.class);

    @Test
    public void testToDto_HappyPath_ReturnedConvertToDto() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        WorkerDto workerDto = workerMapper.toDto(worker);

        assertThat(workerDto).isNotNull();
        assertThat(workerDto.getId()).isEqualTo(worker.getId());
        assertThat(workerDto.getFirstName()).isEqualTo(worker.getFirstName());
        assertThat(workerDto.getLastName()).isEqualTo(worker.getLastName());
        assertThat(workerDto.getPosition()).isEqualTo(worker.getPosition());
        assertThat(workerDto.getSalary()).isEqualTo(worker.getSalary());
    }

    @Test
    public void testToDto_NullWorker() {
        WorkerDto workerDto = workerMapper.toDto(null);

        assertThat(workerDto).isNull();
    }

    @Test
    public void testToEntity_ReturnedConvertToEntity() {
        CreateWorkerCommand createWorkerCommand = CreateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1500.00)
                .build();

        Worker worker = workerMapper.toEntity(createWorkerCommand);

        assertThat(worker).isNotNull();
        assertThat(worker.getFirstName()).isEqualTo(createWorkerCommand.getFirstName());
        assertThat(worker.getLastName()).isEqualTo(createWorkerCommand.getLastName());
        assertThat(worker.getPosition()).isEqualTo(createWorkerCommand.getPosition());
        assertThat(worker.getSalary()).isEqualTo(createWorkerCommand.getSalary());
    }

    @Test
    public void testToEntity_NullCommand() {
        Worker worker = workerMapper.toEntity(null);

        assertThat(worker).isNull();
    }

    @Test
    public void testUpdateEntity_ReturnedUpdateToEntity() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        UpdateWorkerCommand updateWorkerCommand = UpdateWorkerCommand.builder()
                .firstName("Bartek")
                .lastName("Kajtek")
                .position("Tester")
                .salary(1600.00)
                .build();

        workerMapper.updateEntity(updateWorkerCommand, worker);

        assertThat(worker.getFirstName()).isEqualTo(updateWorkerCommand.getFirstName());
        assertThat(worker.getLastName()).isEqualTo(updateWorkerCommand.getLastName());
        assertThat(worker.getPosition()).isEqualTo(updateWorkerCommand.getPosition());
        assertThat(worker.getSalary()).isEqualTo(updateWorkerCommand.getSalary());
    }

    @Test
    public void testUpdateEntity_NullCommand() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        workerMapper.updateEntity(null, worker);

        assertThat(worker.getFirstName()).isEqualTo("Bartek");
        assertThat(worker.getLastName()).isEqualTo("Anczewski");
        assertThat(worker.getPosition()).isEqualTo("Java Developer");
        assertThat(worker.getSalary()).isEqualTo(2000.00);
    }

    @Test
    public void testPartiallyUpdateEntity_ReturnedPartiallyUpdateConvertToEntity() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand = PartiallyUpdateWorkerCommand.builder()
                .lastName("Partially Updated")
                .build();

        workerMapper.partiallyUpdateEntity(partiallyUpdateWorkerCommand, worker);

        assertThat(worker.getLastName()).isEqualTo(partiallyUpdateWorkerCommand.getLastName());
        assertThat(worker.getFirstName()).isEqualTo("Bartek");
        assertThat(worker.getPosition()).isEqualTo("Java Developer");
        assertThat(worker.getSalary()).isEqualTo(2000.00);
    }

    @Test
    public void testPartiallyUpdateEntity_NullCommand() {
        Worker worker = Worker.builder()
                .id(1)
                .firstName("Bartek")
                .lastName("Anczewski")
                .position("Java Developer")
                .salary(2000.00)
                .build();

        workerMapper.partiallyUpdateEntity(null, worker);

        assertThat(worker.getFirstName()).isEqualTo("Bartek");
        assertThat(worker.getLastName()).isEqualTo("Anczewski");
        assertThat(worker.getPosition()).isEqualTo("Java Developer");
        assertThat(worker.getSalary()).isEqualTo(2000.00);
    }
}
