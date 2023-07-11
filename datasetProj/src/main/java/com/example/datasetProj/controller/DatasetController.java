package com.example.datasetProj.controller;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.service.DatasetService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Validated
@RestController
@RequestMapping("/dataset")
public class DatasetController {
    public final DatasetService datasetService;
    public String getRequestUrl(HttpServletRequest request){
        String requestUrl = request.getRequestURL().toString();
        return requestUrl;
    }


    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDataset(@RequestBody @Valid Dataset dataset, @ModelAttribute("requestUrl") String requestUrl) {
        Dataset saveData = datasetService.createDataset(dataset);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("id",requestUrl);
        responseBody.put("ver", "v1");
        responseBody.put("ts", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
        responseBody.put("responseCode","ok");
        responseBody.put("params", new HashMap<>());

        Map<String, Object> result = new HashMap<>();
        result.put("datasets", new ArrayList<>());
        result.put("count",0);
        responseBody.put("result", result);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

//        catch (Exception e) {
//            Map<String, Object> response = new HashMap<>();
//            response.put("timestamp", System.currentTimeMillis());
//            response.put("status", "failed");
//            response.put("error_msg",e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Map<String, Object>) response);
//        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Dataset>> getAll(){
        List<Dataset> datasets = datasetService.getAll();
        return ResponseEntity.ok(datasets);
    }

    @GetMapping("/get/{id}")
    public Optional<Dataset> getDataById(@PathVariable("id") @NotNull String id){
//        Optional<Dataset> getData = datasetService.getDatasetById(id);
//        return getDataById(id);

        return datasetService.getDatasetById(id);
    }



}

