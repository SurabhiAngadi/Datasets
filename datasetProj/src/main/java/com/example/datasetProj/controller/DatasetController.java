package com.example.datasetProj.controller;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/dataset")
public class DatasetController {
    public final DatasetService datasetService;

    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @PostMapping("/create")
    public ResponseEntity<Dataset> createDataset(@RequestBody Dataset dataset) {
        try {
            Dataset saveData = datasetService.createDataset(dataset);
            return ResponseEntity.status(HttpStatus.OK).body(saveData);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", System.currentTimeMillis());
            response.put("status", "failed");
            response.put("error_msg",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Dataset) response);
        }
    }
    @GetMapping("/get")
    public ResponseEntity<List<Dataset>> getAll(){
        List<Dataset> datasets = datasetService.getAll();
        return ResponseEntity.ok(datasets);
    }

    @GetMapping("/get/{id}")
    public Dataset getDataById(@PathVariable("id") String id){
        Optional<Dataset> getData = datasetService.getDatasetById(id);
        return getDataById(id);
    }



}

