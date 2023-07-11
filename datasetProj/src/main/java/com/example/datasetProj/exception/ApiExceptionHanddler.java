package com.example.datasetProj.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@ControllerAdvice
public class ApiExceptionHanddler implements ApiExceptionHanddlerinterface {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errors = fieldErrors
                .stream()
                .map(err -> err.getField() + ":" + err.getDefaultMessage())
                .collect(Collectors.toList());

        ApiException apiException = new ApiException();
        apiException.setStatus(HttpStatus.BAD_REQUEST);
        apiException.setErrors(errors);
        apiException.setTimestamp(LocalDateTime.now());
        apiException.setPath(request.getDescription(false));
        return new ResponseEntity<>(apiException, new HttpHeaders(), apiException.getStatus());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, ServletWebRequest request) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errors = constraintViolations
                .stream()
                .map(err -> err.getRootBeanClass().getName() + " " +
                        err.getPropertyPath() + ": " + err.getMessage())
                .collect(Collectors.toList());

        ApiException apiException = new ApiException();
        apiException.setStatus(HttpStatus.BAD_REQUEST);
        apiException.setErrors(errors);
        apiException.setTimestamp(LocalDateTime.now());
        apiException.setPath(request.getRequest().getRequestURI());
        return new ResponseEntity<>(
                apiException, new HttpHeaders(), apiException.getStatus());
    }


}
