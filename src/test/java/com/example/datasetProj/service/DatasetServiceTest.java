package com.example.datasetProj.service;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.repository.DatasetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DatasetServiceTest {
    @MockBean
    private DatasetRepository datasetRepository;
    @InjectMocks
    private DatasetService datasetService;

    private Dataset dataset;

    @BeforeEach
    void init() {
        dataset = new Dataset();
        dataset.setUuid(UUID.fromString("987922b6-640e-4b37-9707-f14f2b3314a4"));
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
    }


    @Test
    void testCreateDataset(){
        Dataset data = new Dataset();
        UUID uuid = UUID.fromString("987922b6-640e-4b37-9707-f14f2b3314a4");
        data.setUuid(uuid);
        data.setName("datasetEmployeeTwo");
        data.setData_schema(new HashMap<>());
        data.setRouter_config(new HashMap<>());
        data.setStatus(Dataset.Status.Draft);
        data.setCreated_by("datasetEmployeeTwo");
        data.setUpdated_by("datasetEmployeeTwo");
        data.setCreatedDate(System.currentTimeMillis());
        data.setUpdatedDate(System.currentTimeMillis());
        when(datasetRepository.save(any(Dataset.class))).thenReturn(data);
        assertThat(datasetService.createDataset(data)).isEqualTo(dataset);
    }

    @Test
    void save_failure(){
        dataset = new Dataset();
        dataset.setUuid(UUID.randomUUID());
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        Dataset db = datasetService.createDataset(dataset);
        assertNull(db);
    }
    @Test
    void getDatasetById_success() {
        dataset = new Dataset();
        dataset.setUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"));
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.getDatasetByUuid(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        Dataset data = datasetService.getDatasetByUuid(dataset.getUuid());
        assertNotNull(data);
        assertThat(data.getUuid()).isNotEqualTo(null);
    }

    @Test
    void getDatasetById_failure() {
        when(datasetRepository.findById(UUID.fromString("02579671-991e-47be-addd-d7f87ed1641b"))).thenThrow(new NoSuchElementException("User Not Found"));
        Exception ex = assertThrows(RuntimeException.class, () -> {
            datasetService.getDatasetByUuid(dataset.getUuid());
        });

    }
}

