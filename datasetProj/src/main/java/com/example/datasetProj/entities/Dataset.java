package com.example.datasetProj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "datasets")
public class Dataset {
    public enum Status{
        Live,
        Draft,
        retired
    }
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "data_schema", columnDefinition = "json")
    private Object data_schema;

    @Column(name = "router_config", columnDefinition = "json")
    private Object router_config;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}

