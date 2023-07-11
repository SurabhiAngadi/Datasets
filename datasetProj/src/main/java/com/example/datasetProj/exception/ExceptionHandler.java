package com.example.datasetProj.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionHandler{
    private Date timestamp;
    private String message;
    private String details;


}
