package com.example.datasetProj.repository;

import com.example.datasetProj.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface DatasetRepository extends JpaRepository<Dataset, String> {

    Dataset findById(UUID id);



}
