package com.example.datasetProj.controller;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataset")
public class DatasetController {
    @Autowired
    private DatasetService datasetService;

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }


    @PostMapping("/create")
    public Dataset createDataset(@RequestBody Dataset dataset) {
        datasetService.saveDataset(dataset);
        return dataset;
    }
}

