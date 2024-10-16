package org.example.WorkerLibrary.mapper;

import org.example.WorkerLibrary.model.Worker;
import org.example.WorkerLibrary.model.command.CreateWorkerCommand;
import org.example.WorkerLibrary.model.command.PartiallyUpdateWorkerCommand;
import org.example.WorkerLibrary.model.command.UpdateWorkerCommand;
import org.example.WorkerLibrary.model.dto.WorkerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorkerMapper {

    WorkerDto toDto(Worker worker);

    Worker toEntity(CreateWorkerCommand createWorkerCommand);

    void updateEntity(UpdateWorkerCommand updateWorkerCommand, @MappingTarget Worker worker);

    void partiallyUpdateEntity(PartiallyUpdateWorkerCommand partiallyUpdateWorkerCommand, @MappingTarget Worker worker);
}

