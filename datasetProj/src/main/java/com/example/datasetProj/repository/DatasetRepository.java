package com.example.datasetProj.repository;

import com.example.datasetProj.entities.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, String> {
}
