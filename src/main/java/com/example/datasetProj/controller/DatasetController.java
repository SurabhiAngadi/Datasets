package com.example.datasetProj.controller;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.exception.ResponseHandler;
import com.example.datasetProj.repository.DatasetRepository;
import com.example.datasetProj.service.DatasetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import static java.util.Optional.*;

@RestController
@RequestMapping("/dataset")
public class DatasetController {

    @Autowired
    public DatasetService datasetService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Value("${spring.datasource.url}")
    private String jdbcurl;

    @PostConstruct
    private void init(){
        System.out.println("jdbc url "+ jdbcurl);
    }

    @PostMapping("/create")
    public ResponseHandler createDataset(@RequestBody String dataset) throws Exception {
        System.out.println();
        ObjectMapper objectMapper = new ObjectMapper();
        File schemaFile = new File("/home/sankethika/IdeaProjects/Datasets/src/main/java/com/example/datasetProj/schema/schema.json");
        JsonNode jsonSchema = objectMapper.readTree(schemaFile);
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(schemaFile.toURI());
        JsonNode jsonSchema1 = objectMapper.readTree(dataset);
        Set<ValidationMessage> errors = schema.validate(jsonSchema1);
        String combindErrors = "";
        for (ValidationMessage error : errors) {
            combindErrors += error.toString() + "\n";
        }
        if (errors.size() > 0)
            throw new RuntimeException(combindErrors);
        Dataset dataset1 = objectMapper.readValue(dataset, Dataset.class);
        datasetService.createDataset(dataset1);
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setId("api.dataset.create");
        responseHandler.setVer("v1");
        responseHandler.setParam(new HashMap<>());
        Map<String, Object> id = new HashMap<>();
        id.put("id", dataset1.getUuid());
        responseHandler.setResult(id);
        return responseHandler;
    }

    @GetMapping("/get/{id}")
    public ResponseHandler getDataById(@PathVariable() UUID id) {
        Optional<Dataset> optional = Optional.ofNullable(datasetService.getDatasetByUuid(id));
        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setId("api.dataset.get");
        responseHandler.setVer("v1");
        responseHandler.setParam(new HashMap<>());
        Map<String, Object> data = new HashMap<>();
        data.put("dataset", optional.get());
        responseHandler.setResult(data);
        return responseHandler;
    }

}


