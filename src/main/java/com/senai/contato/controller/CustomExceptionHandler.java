package com.senai.contato.controller;

import com.senai.contato.dto.ResponseErrorDTO;
import com.senai.contato.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> handleAllExceptions(ResourceNotFoundException ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(responseErrorDTO, HttpStatusCode.valueOf(responseErrorDTO.status()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseErrorDTO> handleAllExceptions(ValidationException ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(responseErrorDTO, HttpStatusCode.valueOf(responseErrorDTO.status()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleAllExceptions(Exception ex) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(responseErrorDTO, HttpStatusCode.valueOf(responseErrorDTO.status()));
    }

}

