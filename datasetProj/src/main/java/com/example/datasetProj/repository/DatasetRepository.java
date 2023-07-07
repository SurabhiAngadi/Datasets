package com.example.datasetProj.repository;

import com.example.datasetProj.entities.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, String> {
}
