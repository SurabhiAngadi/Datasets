package com.example.datasetProj.controller;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.exception.ResponseHandler;
import com.example.datasetProj.service.DatasetService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import static java.util.Optional.*;

@Validated
@RestController
@RequestMapping("/dataset")
public class DatasetController {
    public final DatasetService datasetService;
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }
    @PostMapping("/create")
    public ResponseHandler createDataset(@RequestBody @Valid Dataset dataset) {
            if (dataset.getStatus() == null) {
                dataset.setStatus(Dataset.Status.Draft);
            }
            datasetService.createDataset(dataset);
            Map<String, Object> param = new HashMap<>();
            param.put("errmsg", " ");

            Map<String, Object> success = new HashMap<>();
            success.put("id", dataset.getId());

            return new ResponseHandler("api.dataset.create", "v1", param, success);

    }
    @GetMapping("/get/{id}")
    public ResponseHandler getDataById(@PathVariable()UUID id) {
        Optional<Dataset> optional = ofNullable(datasetService.getDatasetById(id));
        Map<String ,Object> param = new HashMap<>();
        param.put("errmsg"," ");

        Map<String , Object> success =  new HashMap<>();
        success.put("dataset",optional.get());

        return new ResponseHandler("api.dataset.get", "v1", param, success);
    }
}




