package com.example.datasetProj.repository;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.entities.Dataset.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
@Repository
public interface DatasetRepository extends JpaRepository<Dataset, String> {

    Dataset findById(UUID id);



}
