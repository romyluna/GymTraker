package com.proyecto.gymtracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //cuando un dto no cumple con las condiciones ej:grupomuscularpostDto lanza esta excepcion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //vamos a devolver un json con la estructura definida en la clase:ErrorResponse
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> messages = ex.getBindingResult()//devuelve una lista de todos los errores del dto
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getDefaultMessage())//obtiene solo los mensajes de error que hay en los dto
                .collect(Collectors.toList());//convierte el stream en una lista de Strings con todos los mensajes.

        String combinedMessage = String.join(", ", messages); //Une todos los mensajes de error separados por coma y espacio, para que quede una sola línea de mensaje legible en el JSON

        ErrorResponse errorResponse = new ErrorResponse(
                combinedMessage,
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud incorrecta"
        );

        //Envía al cliente un JSON con el ErrorResponse y el status HTTP 400 (Bad Request)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
