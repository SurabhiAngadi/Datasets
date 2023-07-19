package com.example.datasetProj.service;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.exception.ResponseHandler;
import com.example.datasetProj.repository.DatasetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DatasetServiceTest {
    @Mock
    private DatasetRepository datasetRepository;
    @InjectMocks
    private DatasetService datasetService;

    private Dataset dataset;

    @BeforeEach
    void init() {
        dataset = new Dataset();
        dataset.setId(UUID.randomUUID());
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void save_success() {
        dataset = new Dataset();
        dataset.setId(UUID.randomUUID());
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetRepository.save(any(Dataset.class))).thenReturn(dataset);
        Dataset db = datasetService.createDataset(dataset);
        assertNotNull(db);
        assertEquals(dataset, db);

    }

        @Test
    void save_failure(){
        dataset = new Dataset();
        dataset.setId(UUID.randomUUID());
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
//        when(datasetRepository.save(any(Dataset.class))).thenReturn(dataset);
        Dataset db = datasetService.createDataset(dataset);
        assertNull(db);
    }
    @Test
    void getById_success() {
        dataset = new Dataset();
        dataset.setId(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy("Qwerty");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        when(datasetService.getDatasetById(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        Dataset data = datasetService.getDatasetById(dataset.getId());
        assertNotNull(data);
        assertThat(data.getId()).isNotEqualTo(null);
    }

    @Test
    void getDatasetById_failure() {
        when(datasetRepository.findById(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenThrow(new NoSuchElementException("User Not Found"));
        Exception ex = assertThrows(RuntimeException.class, () -> {
            datasetService.getDatasetById(dataset.getId());
        });

    }
}

