package com.example.datasetProj.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@NoArgsConstructor
@Component
@ControllerAdvice
public class ResponseHandler extends ResponseEntityExceptionHandler {
    private String id;
    private String ver;
    private Map<String , Object> param ;
    private Map<String , Object> result ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.create");
        responseBody.put("ver", "v1");
        List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();

        Map<String, Object> listErrors = new HashMap<>();

        for (FieldError fieldError : fieldErrors){
            String errorMessage = fieldError.getDefaultMessage();
            listErrors.put("errmsg",errorMessage);
        }
        responseBody.put("params",listErrors);
        responseBody.put("result",new HashMap<>());

        return new ResponseEntity<>(responseBody,headers,status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id","api.dataset.get");
        responseBody.put("ver","v1");
        Map<String,Object> param = new LinkedHashMap<>();
        param.put("errormsg","Invalid Id");
        responseBody.put("param",param);
        responseBody.put("result", new HashMap<>());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);

    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception){
        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("id", "api.dataset.get");
        responseBody.put("ver", "v1");
        Map<String,Object> param = new LinkedHashMap<>();
        param.put("errormsg","No DatasetFound");
        responseBody.put("param",param);
        responseBody.put("result",new HashMap<>());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
