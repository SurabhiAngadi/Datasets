package com.example.datasetProj.controller;

import com.example.datasetProj.entities.Dataset;
import com.example.datasetProj.service.DatasetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DatasetController.class)
@ExtendWith(MockitoExtension.class)
class DatasetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatasetService datasetService;
    @Autowired
    private ObjectMapper objectMapper;

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
    void testCreateDataset_success() throws Exception {

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

        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);

        this.mockMvc.perform(post("/dataset/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataset)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testCreateDataset_fail() throws Exception {
        dataset = new Dataset();
        dataset.setId(UUID.randomUUID());
        dataset.setName("Surabhi123");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreatedBy("Surabhi");
        dataset.setUpdatedBy(null);
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());

        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);

        this.mockMvc.perform(post("/dataset/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataset)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testGetByid_success() throws Exception {
        when(datasetService.getDatasetById(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/51ababe2-a1e7-48ac-9e0d-c8be18eeadea",UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetByid_fail() throws Exception {
        when(datasetService.getDatasetById(UUID.fromString("51ababe2-a1e7-48ac-9e0d-c8be18eeadea"))).thenReturn(dataset);
        this.mockMvc.perform(get("/dataset/get/51ababe2-a1e7-48ac-9e0d")).andDo(print()).andExpect(status().isBadRequest());
    }


}


