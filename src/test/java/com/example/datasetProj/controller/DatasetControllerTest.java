package com.example.datasetProj.controller;

import com.example.datasetProj.model.Dataset;
import com.example.datasetProj.service.DatasetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
class DatasetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatasetService datasetService;
    @Autowired
    private ObjectMapper objectMapper;

    private Dataset dataset;

    @Test
    void testCreateDataset_success() throws Exception {

        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");
        String id = (String) result.get("id");
        assertEquals(uuid.toString(), id);
        assertEquals(200,status);
    }

    @Test
    void testInvlidName() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Surabhi123");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String errMsg = (String) result.get("errormsg");
        assertEquals("$.name: does not match the regex pattern ^[a-zA-Z]+$\n",errMsg);
        assertEquals(400,status);
    }

    @Test
    void testNameMinLengthRequired() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Sam");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String errMsg = (String) result.get("errormsg");
        assertEquals("$.name: must be at least 5 characters long\n",errMsg);
        assertEquals(400,status);
    }

    @Test
    void testStatusShouldNotBeNull() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Sampada");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(null);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String errMsg = (String) result.get("errormsg");
        assertEquals("$.status: does not have a value in the enumeration [Draft, Live, Retired]\n$.status: null found, string expected\n",errMsg);
        assertEquals(400,status);
    }

    @Test
    void testCreatedbyShouldNotBenull() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Sampada");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by(null);
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String errMsg = (String) result.get("errormsg");
        assertEquals("$.created_by: null found, string expected\n",errMsg);
        assertEquals(400,status);
    }

    @Test
    void testUpdatedbyShouldNotBenull() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Sampada");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Draft);
        dataset.setCreated_by("Spring");
        dataset.setUpdated_by(null);
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.createDataset(any(Dataset.class))).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(post("/dataset/create").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String errMsg = (String) result.get("errormsg");
        assertEquals("$.updated_by: null found, string expected\n",errMsg);
        assertEquals(400,status);
    }


    @Test
    void testGetByid_success() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Live);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.getDatasetByUuid(uuid)).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("result");
        Map<String, Object> data = (Map<String, Object>) result.get("dataset");
        assertEquals(200,status);
    }

    @Test
    void testForInvalidId() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Live);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.getDatasetByUuid(uuid)).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/abcdefghi").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errormsg");
        assertEquals("Invalid Id",msg);
        assertEquals(400,status);
    }

    @Test
    void testForNoDatasetFound() throws Exception {
        dataset = new Dataset();
        UUID uuid = UUID.fromString("e12bf1cf-28aa-4c51-b6b8-813a7e4ad7f9");
        dataset.setUuid(uuid);
        dataset.setName("Surabhi");
        dataset.setData_schema(new HashMap<>());
        dataset.setRouter_config(new HashMap<>());
        dataset.setStatus(Dataset.Status.Live);
        dataset.setCreated_by("Surabhi");
        dataset.setUpdated_by("Qwerty");
        dataset.setCreatedDate(System.currentTimeMillis());
        dataset.setUpdatedDate(System.currentTimeMillis());
        when(datasetService.getDatasetByUuid(uuid)).thenReturn(dataset);
        MvcResult mvcResult = mockMvc.perform(get("/dataset/get/e12bf1cf-28aa-4c51-b6b8-813a7e12345").content(objectMapper.writeValueAsString(dataset)).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        System.out.println(response.getContentAsString());
        int status = response.getStatus();
        Map<String, Object> responseMap= objectMapper.readValue(response.getContentAsString(), Map.class);
        Map<String, Object> result = (Map<String, Object>) responseMap.get("param");
        String msg = (String) result.get("errormsg");
        assertEquals("No DatasetFound",msg);
        assertEquals(400,status);
    }


}


