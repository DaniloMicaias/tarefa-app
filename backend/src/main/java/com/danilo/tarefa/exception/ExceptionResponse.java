package com.danilo.tarefa.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionResponse implements Serializable {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
