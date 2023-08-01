package com.example.datasetProj.repository;

import com.example.datasetProj.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DatasetRepository extends JpaRepository<Dataset, String> {
    public Dataset findByUuid(UUID uuid);
}
