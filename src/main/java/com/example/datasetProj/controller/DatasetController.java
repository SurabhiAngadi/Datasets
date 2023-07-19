package com.example.datasetProj.controller;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.exception.ResponseHandler;
import com.example.datasetProj.service.DatasetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
            ResponseHandler responseHandler = new ResponseHandler();
            responseHandler.setId("api.dataset.create");
            responseHandler.setVer("v1");
            responseHandler.setParam(new HashMap<>());
            Map<String,Object> id = new HashMap<>();
            id.put("id",dataset.getId());
            responseHandler.setResult(id);

            return responseHandler;
    }
    @GetMapping("/get/{id}")
    public ResponseHandler getDataById(@PathVariable()UUID id) {
        Optional<Dataset> optional = ofNullable(datasetService.getDatasetById(id));
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setId("api.dataset.get");
        responseHandler.setVer("v1");
        responseHandler.setParam(new HashMap<>());
        Map<String,Object> data = new HashMap<>();
        data.put("dataset",optional.get());
        responseHandler.setResult(data);

        return responseHandler;
    }
}




