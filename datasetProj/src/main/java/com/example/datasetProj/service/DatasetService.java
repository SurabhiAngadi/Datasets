package com.example.datasetProj.service;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DatasetService {
    @Autowired
    private  DatasetRepository datasetRepository;


    @Transactional
    public Dataset createDataset(Dataset dataset) {

        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        return datasetRepository.save(dataset);
    }

    public List<Dataset> getAll(){
        return datasetRepository.findAll();
    }

    public Optional<Dataset> getDatasetById(String id){
        return datasetRepository.findById(id);
    }

}
