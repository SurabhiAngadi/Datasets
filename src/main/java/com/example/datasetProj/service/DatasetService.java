package com.example.datasetProj.service;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;

    @Transactional
    public Dataset createDataset(Dataset dataset) {
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        return datasetRepository.save(dataset);
    }
    @Transactional
    public Dataset getDatasetByUuid(UUID uuid) {
        System.out.println(datasetRepository.findByUuid(uuid));
       return datasetRepository.findByUuid(uuid);

    }

}


