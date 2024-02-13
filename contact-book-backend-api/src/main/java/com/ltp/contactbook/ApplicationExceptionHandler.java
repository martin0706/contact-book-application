package com.ltp.contactbook;


import com.ltp.contactbook.exception.AddressNotFoundException;
import com.ltp.contactbook.exception.EmailNotFoundException;
import com.ltp.contactbook.exception.ErrorResponse;
import com.ltp.contactbook.exception.PersonNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class, EmailNotFoundException.class, AddressNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Collections.singletonMap("global", List.of(ex.getMessage())));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse error = new ErrorResponse(Collections.singletonMap("global", List.of("Cannot delete non-existing resource")));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(ConstraintViolationException ex) {
        Map<String, List<String>> validationErrors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = extractFieldName(violation.getPropertyPath().toString());
            String errorMessage = violation.getMessage();

            validationErrors.computeIfAbsent(propertyPath, k -> new ArrayList<>()).add(errorMessage);
        }
        ErrorResponse errorResponse = new ErrorResponse(validationErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> validationErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            validationErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            String errorMessage = objectError.getDefaultMessage();
            validationErrors.computeIfAbsent("global", k -> new ArrayList<>()).add(errorMessage);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse(validationErrors));
    }

    private String extractFieldName(String propertyPath) {
        int lastIndex = propertyPath.lastIndexOf(".");
        return lastIndex != -1 ? propertyPath.substring(lastIndex + 1) : propertyPath;
    }

}
