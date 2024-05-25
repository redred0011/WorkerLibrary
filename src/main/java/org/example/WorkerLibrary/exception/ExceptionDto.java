package org.example.WorkerLibrary.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ExceptionDto {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
}