package com.example.datasetProj.service;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatasetService {
    private final DatasetRepository datasetRepository;

    @Autowired
    public DatasetService(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    @Transactional
    public void saveDataset(Dataset dataset) {
        datasetRepository.save(dataset);
    }



}
