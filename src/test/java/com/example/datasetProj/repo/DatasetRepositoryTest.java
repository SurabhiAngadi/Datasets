package com.example.datasetProj.repo;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatasetRepositoryTest {
    @Autowired
    private DatasetRepository datasetRepository;

    private Dataset dataset;

    @BeforeEach
    void init() {
        dataset = new Dataset();
        dataset.setId(UUID.fromString("de5ee65b-fc16-4816-9c22-c4e9d2d562f4"));
        dataset.setName("Sanketika");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
    }

    @AfterEach
    void teardown(){

    }

    @Test
    @DisplayName("Should return dataset by its id")
    void getDatasetById_success() {
        Dataset db = datasetRepository.findById(UUID.fromString("de5ee65b-fc16-4816-9c22-c4e9d2d562f4"));
        assertTrue(db.getId() != null);
        assertThat(db.getId()).isEqualTo(dataset.getId());
    }

    @Test
    void testDatasetFindByIdError(){
        Optional<Dataset> optional = Optional.ofNullable(datasetRepository.findById(UUID.fromString("57093425-5fc7-40ef-9876-cd06412fe3e4")));
        assertThat(optional.isEmpty()).isTrue();
    }






    }





