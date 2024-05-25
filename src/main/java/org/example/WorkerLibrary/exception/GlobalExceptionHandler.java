package org.example.WorkerLibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class, WorkerNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleNotFoundExceptions(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(WorkerUpdateException.class)
    public ResponseEntity<ExceptionDto> handleWorkerUpdateException(WorkerUpdateException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(WorkerPartiallyUpdateException.class)
    public ResponseEntity<ExceptionDto> handleWorkerPartiallyUpdateException(WorkerPartiallyUpdateException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGeneralException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDto(exception.getMessage()));

    }
}
