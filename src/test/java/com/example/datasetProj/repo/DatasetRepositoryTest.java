//package com.example.datasetProj.repo;
//
//import com.example.datasetProj.model.Dataset;
//import com.example.datasetProj.repository.DatasetRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mockStatic;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class DatasetRepositoryTest {
//    @Autowired
//    private DatasetRepository datasetRepository;
//
//    private Dataset dataset;
//
//    @BeforeEach
//    void init() {
//        dataset = new Dataset();
//        dataset.setUuid(UUID.fromString("b777c2d0-599c-4e58-ad95-78caa75e475d"));
//        dataset.setName("Sanketika");
//        dataset.setData_schema(new HashMap<>());
//        dataset.setRouter_config(new HashMap<>());
//        dataset.setStatus(Dataset.Status.Draft);
//        dataset.setCreated_by("Surabhi");
//        dataset.setUpdated_by("Qwerty");
//        dataset.setCreatedDate(System.currentTimeMillis());
//        dataset.setUpdatedDate(System.currentTimeMillis());
//    }
//
//    @AfterEach
//    void teardown() {
//
//    }
//
////    @Test
////    void getDatasetById_success() {
////        Dataset db = datasetRepository.findByUuid(UUID.fromString("b777c2d0-599c-4e58-ad95-78caa75e475d"));
////        assertTrue(db.getUuid() != null);
////        assertThat(db.getUuid()).isEqualTo(dataset.getUuid());
////    }
////
////    @Test
////    void testDatasetFindByIdError() {
////        Dataset optional = datasetRepository.findByUuid(UUID.fromString("57093425-5fc7-40ef-9876-cd06412fe3e4"));
////        assertThat(optional.equals(null)).isTrue();
////    }
//
//
//}
//
//
//
//
//
