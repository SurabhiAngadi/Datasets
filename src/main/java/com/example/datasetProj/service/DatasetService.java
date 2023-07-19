package com.example.datasetProj.service;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DatasetService {
    @Autowired
    private DatasetRepository datasetRepository;

    @Transactional
    public Dataset createDataset(Dataset dataset) {
        if(dataset.status.equals(null)) {
            dataset.setStatus(Dataset.Status.Draft);
        }
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        return datasetRepository.save(dataset);
    }

    public Dataset getDatasetById(UUID id) {
        return datasetRepository.findById(id);
    }

}


